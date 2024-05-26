# Login-Project

- [x] E-Mail verifizieren
- [x] Passwort verifizieren
- [ ] .warnung beim ersten Mal deaktivieren
- [ ] confirm mit E-Mail
- [ ] Passwort vergessen
- [ ] Tab-Reihenfolge
- [ ] abmelden in der Applikation

## Salt
- [ ] SecureRandom, 16/32 Byte
```
SecureRandom random = new SecureRandom();
byte[] salt = new byte[32];
random.nextBytes(salt);

String saltString = Base64.getUrlEncoder().encodeToString(salt);
```

## Hash
- [ ] PBKDF2WithHmacSHA1 oder bcrypt
- [ ] Unabhängig von der gewählten Methode ist es wichtig, den Salt niemals zusammen mit dem Passwort-Hash zu übertragen

### prepaired statement

## Code-Schnipsel
//        Todo: Überprüfen, ob die Enter-Taste am Mac funktioniert:
//        emailField.getParent().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            if (event.getCode() != KeyCode.ENTER) return;
//            if ((event.getTarget() instanceof Button)) ((Button) event.getTarget()).fire();
//        });

# Database
public class Main {
public static void main(String[] args) {
String url = "jdbc:sqlite:db/lakes_db.db";
try (Connection conn = DriverManager.getConnection(url, "", "");
Statement stmt = conn.createStatement()) {

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

//            Create table lakes
//            if (!conn.getMetaData().getTables(null, null, "lakes", null).next()) {
//                sqlStmts.add("CREATE TABLE lakes(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, lakeName VARCHAR(50))");
//                System.out.println("Table created");
//            }
updateDb(stmt, sqlStmts);
sqlStmts.clear();
printSelectedAll(stmt, "countries");
printSelectedAll(stmt, "lakes");

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

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

    /**
     * updates database – accesses the statements in sqlStatements
     * @param stmt     Statement
     * @param sqlStmts ArrayList<String>
     * @throws SQLException e
     */
    private static void updateDb(Statement stmt, ArrayList<String> sqlStmts) throws SQLException {
        for (String sql : sqlStmts) {
            stmt.executeUpdate(sql);
        }
    }
}