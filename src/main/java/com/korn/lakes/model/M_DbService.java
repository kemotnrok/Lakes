package com.korn.lakes.model;

import com.korn.lakes.model.DTO.Lake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.korn.lakes.model.M_DbCommunication.queryLakeDb;
import static com.korn.lakes.model.M_DbCommunication.queryUsersDb;

public class M_DbService {

//    ---------- SQL prepared queries

    //    User
    private static final String findUser = "select e_mail_hash, salt, password_hash from users where e_mail_hash like '%s'";
    private static final String findPassword = "select password_hash from users where e_mail_hash like '%s'";
    private static final String updateUser = "INSERT INTO users (e_mail_hash, salt) VALUES('%s', '%s');";
    private static final String updateUserPassword = "UPDATE users SET password_hash = '%s', salt ='%s' where e_mail_hash like '%s'";
    private static final String insertUserPassword = "UPDATE users SET password_hash ='%s' where e_mail_hash like '%s'";
    //    private static final String deleteUser = "Delete * from users where e_mail_hash like '%s'"; // todo

    //    Lakes
    private static final String findAllLakes = "SELECT lakeName, countryName, expance, seeLevel, temperature, rating, note\n" +
            "FROM lakes l\n" +
            "INNER JOIN lake_country_relation lcr ON l.id = lcr.lakeId\n" +
            "INNER JOIN countries c ON c.id = lcr.countryId";

//    ---------- Queries to M_DbCommunication

    //    User
    public static ArrayList<HashMap<String, String>> findUser(String data) {
        String sqlStmt = String.format(findUser, data);
        return queryUsersDb(sqlStmt);
    }

    public static ArrayList<HashMap<String, String>> findPassword(String emailHash) {
        String sqlStmt = String.format(findPassword, emailHash);
        return queryUsersDb(sqlStmt);
    }

    public static void createUser(String email, String salt) {
        ArrayList<String> sqlStmts = new ArrayList<>();
        sqlStmts.add(String.format(updateUser, email, salt));
        M_DbCommunication.updateDb(sqlStmts, M_Databases.users_db);
        sqlStmts.clear();
    }

    public static void updateUserPassword(String passwordNew, String salt, String emailHash) {
        ArrayList<String> sqlStmts = new ArrayList<>();
        sqlStmts.add(String.format(updateUserPassword, passwordNew, salt, emailHash));
        M_DbCommunication.updateDb(sqlStmts, M_Databases.users_db);
        sqlStmts.clear();
    }

    public static void insertUserPassword(String passwordHash, String emailHash) {
        ArrayList<String> sqlStmts = new ArrayList<>();
        sqlStmts.add(String.format(insertUserPassword, passwordHash, emailHash));
        M_DbCommunication.updateDb(sqlStmts, M_Databases.users_db);
        sqlStmts.clear();
    }

    //   Lakes
    public static ObservableList<Lake> findLakes() {
        String sqlStmt = String.format(findAllLakes);
        return queryLakeDb(sqlStmt);
    }

//    ---------- Model helper methods

    static ArrayList<HashMap<String, String>> mapUsersResultSet(ResultSet rs) throws SQLException {
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

    protected static ObservableList<Lake> mapLakeResultSet(ResultSet rs) throws SQLException {
        ObservableList<Lake> lakesObservable = FXCollections.observableArrayList();
        while (rs.next()) {
            lakesObservable.add(new Lake(
                    rs.getString("lakeName"),
                    rs.getString("countryName"),
                    rs.getDouble("expance"),
                    rs.getDouble("seeLevel"),
                    rs.getDouble("temperature"),
                    rs.getString("note"),
                    rs.getInt("rating")
            ));
        }
        return lakesObservable;
    }
}
