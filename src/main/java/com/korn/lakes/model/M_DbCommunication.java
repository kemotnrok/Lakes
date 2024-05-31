package com.korn.lakes.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import static com.korn.lakes.model.M_DbService.mapResultSet;

public class M_DbCommunication {

    static String path = "jdbc:sqlite:src/main/resources/com/korn/lakes/db/%s.db";

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

    protected static ArrayList<HashMap<String, String>> queryDb(String sqlStatement, M_Databases db) {
        String url = String.format(path, db);
        try (Connection conn = DriverManager.getConnection(url, "", "");
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlStatement);
            return mapResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

