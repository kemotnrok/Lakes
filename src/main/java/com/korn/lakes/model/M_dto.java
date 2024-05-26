package com.korn.lakes.model;

import java.sql.*;

public class M_dto {
    String urlUsers = "jdbc:sqlite:db/users_db.db";
    String urlLakes = "jdbc:sqlite:db/lakes_db.db";


    private ResultSet transactDb(M_Transactions transaction, String[] sqlStatements, M_Databases db) {
        try (Connection conn = DriverManager.getConnection(urlLakes, "", "");
             Statement stmt = conn.createStatement()) {

            switch (transaction) {
                case M_Transactions.CREATE:
                    create //todo
                case M_Transactions.READ:
                    read //todo
                case M_Transactions.UPDATE:
                    return updateDb(stmt, sqlStatements); //todo
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * updates database – accesses the statements in sqlStatements
     *
     * @param stmt          Statement
     * @param sqlStatements ArrayList<String>
     * @throws SQLException e
     */
    private static boolean updateDb(Statement stmt, String[] sqlStatements) throws SQLException {
        try {
            for (String sql : sqlStatements) {
                stmt.executeUpdate(sql);
            }
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage()); //todo
            return false;
        }
    }
}
