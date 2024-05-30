package com.korn.lakes.model;

import java.sql.*;
import java.util.ArrayList;

public class M_DTO {

    static String path = "jdbc:sqlite:src/main/resources/com/korn/lakes/db/%s.db";

    protected static void dtoUpdateDb(ArrayList<String> sqlStatements, M_Databases db) {
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

    protected static ResultSet dtoQueryDb(String sqlStatement, M_Databases db) {
        String url = String.format(path, db);
        try (Connection conn = DriverManager.getConnection(url, "", "");
             Statement stmt = conn.createStatement()) {
            return stmt.executeQuery(sqlStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

