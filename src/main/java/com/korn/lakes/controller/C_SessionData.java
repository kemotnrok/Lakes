package com.korn.lakes.controller;

import com.korn.lakes.model.DTO.User;

public class C_SessionData {

    private static User viewUser = new User("");
    private static User dbUser = new User("");

//    ----------

    public static User getViewUser() {
        return viewUser;
    }

    public static void setViewUser(User viewUser) {
        C_SessionData.viewUser = viewUser;
    }

    public static User getDbUser() {
        return dbUser;
    }

    public static void setDbUser(User dbUser) {
        C_SessionData.dbUser = dbUser;
    }
}
