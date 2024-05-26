package com.korn.lakes.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class M_dbService {
    ArrayList<String> sqlStmts = new ArrayList<>();
//            ArrayList<String> sqlStmts = new ArrayList<>(Arrays.asList(
//                    "insert into countries (countryName) values" +
//                            "('Austria'), ('Germany'), ('Switzerland'), ('Italy'), " +
//                            "('Slovenia'), ('Poland'), ('Slovakia')"
//            ));
//            ArrayList<String> sqlStmts = new ArrayList<>(Arrays.asList(
//                    "insert into lakes (lakeName) values" +
//                            "('Altausseer See'), ('Attersee'), ('Bodensee'), " +
//                            "('Chiemsee'), ('Fuschlsee'), ('Lunzer See'), " +
//                            "('Mondsee'), ('Neusiedler See'), ('Plattensee'), " +
//                            "('Sniardwy'), ('Traunsee'), ('Weißensee'), ('Wörther See')"
//            ));

//    updateDb(stmt, sqlStmts);
//            sqlStmts.clear();
//
//    printSelectedAll(stmt, "countries");
//
//    printSelectedAll(stmt, "lakes");

//    Create table lakes
//            if (!conn.getMetaData().getTables(null, null, "lakes", null).next()) {
//                sqlStmts.add("CREATE TABLE lakes(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, lakeName VARCHAR(50))");
//                System.out.println("Table created");
//            }

//     --------------------

    /**
     * selects and prints all records from a table
     * @param stmt  Statement
     * @param table String
     * @throws SQLException e
     */
    private static void printSelectedAll(Statement stmt, String table) throws SQLException {
        try (ResultSet rs = stmt.executeQuery("select * from " + table)) {
            System.out.println("\n" + table + ":");
            while (rs.next()) {
                System.out.println(rs.getString(1) + ". " + rs.getString(2));
            }
        }
    }


}
