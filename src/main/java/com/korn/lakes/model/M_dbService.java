package com.korn.lakes.model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class M_dbService {

    private static final String findUser = "select * from users where e_mail_hash like %s";
    private static final String insertUser = "INSERT INTO users (e_mail_hash, password_hash, salt) VALUES(%s, %s, %s);";
    private static final String changeUserEmail = "select * from users where e_mail_hash like ?";
    private static final String changeUserPassword = "select * from users where e_mail_hash like ?";

//    ----------

    public static void updateDB(String data, M_databases db) {
        ArrayList<String> sqlStmts = new ArrayList<>();
        sqlStmts.add(String.format(insertUser, data)); //todo drei Argumente
        M_dto.dtoUpdateDb(sqlStmts, db);
        sqlStmts.clear();
    }

    public static ResultSet queryDB(String data, M_databases db) {
        String sqlStmt = String.format(findUser, data);
        return M_dto.dtoQueryDb(sqlStmt, db);
    }
}
