package com.korn.lakes.controller;

import com.korn.lakes.model.DTO.User;

public class C_Helper_tempData {
    private static String email = "";
    private static String password = "";
    private static User viewUser;
    private static User dbUser;

//    ----------
    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        C_Helper_tempData.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        C_Helper_tempData.password = password;
    }

    public User getViewUser() {
        return viewUser;
    }

    public static void setViewUser(User viewUser) {
        C_Helper_tempData.viewUser = viewUser;
    }

    public static User getDbUser() {
        return dbUser;
    }

    public static void setDbUser(User dbUser) {
        C_Helper_tempData.dbUser = dbUser;
    }
}
