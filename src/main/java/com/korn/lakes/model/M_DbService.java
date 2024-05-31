package com.korn.lakes.model;

import com.korn.lakes.model.DTO.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.korn.lakes.model.M_DbCommunication.queryDb;

public class M_DbService {

    private static final String findUser = "select e_mail_hash, salt, password_hash from users where e_mail_hash like '%s'";
    private static final String insertUser = "INSERT INTO users (e_mail_hash, salt) VALUES('%s', '%s');";
//    private static final String insertUser = "INSERT INTO users (e_mail_hash, password_hash, salt) VALUES('%s', '%s', '%s');";
    private static final String changeUserEmail = "select * from users where e_mail_hash like ?";
    private static final String changeUserPassword = "select * from users where e_mail_hash like ?";
    private static final String deleteUser = "Delete * from users where e_mail_hash like '%s'";

//    ----------

    public static void updateDB(String data, M_Databases db) {
        ArrayList<String> sqlStmts = new ArrayList<>();
        sqlStmts.add(String.format(insertUser, data, 102)); //todo drei Argumente
        M_DbCommunication.updateDb(sqlStmts, db);
        sqlStmts.clear();
    }

    public static ArrayList<HashMap<String, String>> findUser(String data, M_Databases db) {
        String sqlStmt = String.format(findUser, data);
        ArrayList<HashMap<String, String>> result = queryDb(sqlStmt, db);
        return result;
    }

    protected static ArrayList<HashMap<String, String>> mapResultSet(ResultSet rs) throws SQLException {
        ArrayList<HashMap<String, String>> resultList = new ArrayList<>();
        while (rs.next()) {
            HashMap<String, String> row = new HashMap<>();
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String key = rs.getMetaData().getColumnName(i);
                String value = rs.getString(i);
                row.put(key, value);
            }
            resultList.add(row);
        }
        return resultList;
    }
}
