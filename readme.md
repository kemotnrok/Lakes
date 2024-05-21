# Login-Project

- [ ] Email verifizieren
- [ ] Passwort verifizieren
- [ ] an Datenbank anbinden

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
- [ ] Unabhängig von der gewählten Methode ist es wichtig, den Salt nicht zusammen mit dem Passwort-Hash zu übertragen
- [ ] KeyEvent ENTER einrichten für Weiter

### prepaired statement
```
String username = "maxmustermann";
String password = "meinGeheimnis";

try (Connection connection = DriverManager.getConnection(url, user, password)) {
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password_hash = ?");
    statement.setString(1, username);
    statement.setString(2, hashPassword(password, salt));
    ResultSet resultSet = statement.executeQuery();

    // Verarbeiten Sie die Ergebnisse der Abfrage
} catch (SQLException e) {
    e.printStackTrace();
}
```