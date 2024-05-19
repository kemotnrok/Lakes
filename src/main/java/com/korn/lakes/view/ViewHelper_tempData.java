package com.korn.lakes.view;

public class ViewHelper_tempData {
    private static String email;
    private static String password;

//    Getter/Setter
    public String getTempEmail() {
        return email;
    }

    public static void setTempEmail(String email) {
        ViewHelper_tempData.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ViewHelper_tempData.password = password;
    }
}
