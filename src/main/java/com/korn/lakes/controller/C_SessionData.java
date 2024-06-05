package com.korn.lakes.controller;

import com.korn.lakes.model.DTO.User;

public class C_SessionData {

    private static User sessionUser = new User("");
    private static User dbUser = new User("");

//    ----------

    public static User getSessionUser() {
        return sessionUser;
    }

    public static void setSessionUser(User sessionUser) {
        C_SessionData.sessionUser = sessionUser;
    }

    public static User getDbUser() {
        return dbUser;
    }

    public static void setDbUser(User dbUser) {
        C_SessionData.dbUser = dbUser;
    }
}
