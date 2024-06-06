package com.korn.lakes.model;

import com.korn.lakes.model.DTO.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.korn.lakes.model.M_DbCommunication.queryDb;

public class M_DbService {

    private static final String findUser = "select e_mail_hash, salt, password_hash from users where e_mail_hash like '%s'";
    private static final String findPassword = "select password_hash from users where e_mail_hash like '%s'";
    private static final String updateUser = "INSERT INTO users (e_mail_hash, salt) VALUES('%s', '%s');";
    private static final String updateUserPassword = "UPDATE users SET password_hash = '%s', salt ='%s' where e_mail_hash like '%s'";
    private static final String insertUserPassword = "UPDATE users SET password_hash ='%s' where e_mail_hash like '%s'";

    private static final String deleteUser = "Delete * from users where e_mail_hash like '%s'"; // todo

//    ----------

    public static ArrayList<HashMap<String, String>> findUser(String data, M_Databases db) { // todo: eigentlich sollten die Methoden nicht nur für User gelten
        String sqlStmt = String.format(findUser, data);
        return queryDb(sqlStmt, db);
    }
    public static ArrayList<HashMap<String, String>> findPassword(String emailHash, M_Databases db) {
        String sqlStmt = String.format(findPassword, emailHash);
        return queryDb(sqlStmt, db);
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
}
