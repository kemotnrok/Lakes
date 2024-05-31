package com.korn.lakes.model.DTO;

public class User {

    private String email = "";
    private String password = "";
    private String salt = "";

//    ----------

    public User(String email) {
        this.email = email;
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
}
