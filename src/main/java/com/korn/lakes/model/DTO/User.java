package com.korn.lakes.model.DTO;

public class User {

    private String email = "";
    private String emailHash = "";
    private String password = "";
    private String passwordHash = "";
    private String salt = "";

//    ----------

    public User(String email) {
        this.email = email;
    }

//    ----------

    @Override
    public String toString(){
        return String.format("""
                User:
                email: %s
                emailHash: %s
                password: %s
                passwordHash: %s
                salt: %s
                """, email, emailHash, password, passwordHash, salt);
    }

    public boolean equals(User user){
        return this.emailHash.equals(user.emailHash) && this.passwordHash.equals(user.passwordHash);
    }

//    ----------

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}
