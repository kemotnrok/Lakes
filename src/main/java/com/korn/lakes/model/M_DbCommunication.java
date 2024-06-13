package com.korn.lakes.model;

import com.korn.lakes.model.DTO.Lake;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import static com.korn.lakes.model.M_DbService.mapLakeResultSet;
import static com.korn.lakes.model.M_DbService.mapUsersResultSet;

public class M_DbCommunication {

    static String path = "jdbc:sqlite:src/main/resources/com/korn/lakes/db/%s.db";
    private static final Logger logger = LoggerFactory.getLogger(M_DbCommunication.class);

    protected static void updateDb(ArrayList<String> sqlStatements, M_Databases db) {
        String url = String.format(path, db);
        try (Connection conn = DriverManager.getConnection(url, "", "");
             Statement stmt = conn.createStatement()) {
            for (String sqlStmt : sqlStatements) {
                stmt.executeUpdate(sqlStmt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static ArrayList<HashMap<String, String>> queryUsersDb(String sqlStatement) {
        String url = String.format(path, M_Databases.users_db);
        try (Connection conn = DriverManager.getConnection(url, "", "");
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlStatement);
            return mapUsersResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static ObservableList<Lake> queryLakeDb(String sqlStatement) {
        String url = String.format(path, M_Databases.lakes_db);
        try (Connection conn = DriverManager.getConnection(url, "", "");
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlStatement);
            return mapLakeResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//          Der Versuch einer generischen Methode. Klappt nicht, weil ObservableList und ArrayList offenbar nicht castbar
//        protected static Object<T> queryDb(String sqlStatement, M_Databases db) {
//        String url = String.format(path, db);
//        try (Connection conn = DriverManager.getConnection(url, "", "");
//             Statement stmt = conn.createStatement()) {
//            ResultSet rs = stmt.executeQuery(sqlStatement);
//            if (db == M_Databases.lakes_db) {
//                return (Object<T>) mapLakeResultSet(rs);
//            } else return (Object<T>) M_DbService.mapUsersResultSet(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }

}

