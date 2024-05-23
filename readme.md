# Login-Project

- [x] E-Mail verifizieren
- [ ] Passwort verifizieren
- [ ] Passwort vergessen
- [ ] Tabreihenfolge
- [ ] Passwort confirm im E-Mail
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
## Code-Schnipsel
//        Todo: Überprüfen, ob die Enter-Taste am Mac funktioniert:
//        emailField.getParent().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            if (event.getCode() != KeyCode.ENTER) return;
//            if ((event.getTarget() instanceof Button)) ((Button) event.getTarget()).fire();
//        });