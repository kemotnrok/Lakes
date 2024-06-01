package com.korn.lakes.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    private static ArrayList<HashMap<String, String>> mapResultSet(ResultSet rs) throws SQLException {
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

