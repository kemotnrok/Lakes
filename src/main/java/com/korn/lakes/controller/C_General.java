package com.korn.lakes.controller;

import com.korn.lakes.model.DTO.User;
import com.korn.lakes.model.M_Crypto;
import com.korn.lakes.model.M_Databases;
import com.korn.lakes.model.M_DbService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static com.korn.lakes.controller.C_SessionData.getDbUser;
import static com.korn.lakes.controller.C_SessionData.getSessionUser;
import static com.korn.lakes.model.M_DbService.findUser;

public class C_General {

    public static boolean findDbUser(User sessionUser) {
        String sessionEmailHash = M_Crypto.hashEmail(sessionUser.getEmail());
        ArrayList<HashMap<String, String>> result = findUser(sessionEmailHash, M_Databases.users_db);
        getSessionUser().setEmailHash(sessionEmailHash);

        if (result.isEmpty()) return false;
        else {
            HashMap<String, String> row = result.getFirst();
            User dbUser = getDbUser();
            dbUser.setEmailHash(row.get("e_mail_hash"));
            dbUser.setSalt(row.get("salt"));
            dbUser.setPasswordHash(row.get("password_hash"));
            return true;
        }
    }

    public static boolean checkPassword(User sessionUser) {
        String sessionEmailHash = sessionUser.getEmailHash();
        String sessionPassword = sessionUser.getPassword();
        String salt = getDbUser().getSalt();
        String sessionPasswordHash = Objects.requireNonNull(M_Crypto.hashPasswordGivenSalt(sessionPassword, salt));
        ArrayList<HashMap<String, String>> result = M_DbService.findPassword(sessionEmailHash, M_Databases.users_db);
        if (result.isEmpty()) return false;
        else {
            HashMap<String, String> row = result.getFirst();
            String dbPasswordHash = row.get("password_hash");
            getDbUser().setPasswordHash(dbPasswordHash);
            return Objects.equals(dbPasswordHash, sessionPasswordHash);
        }
    }

    public static boolean createUser(User sessionUser) {
        String sessionEmailHash = M_Crypto.hashEmail(sessionUser.getEmail());
        String sessionPassword = sessionUser.getPassword();
        String[] sessionPassHashAndSalt = Objects.requireNonNull(M_Crypto.hashPasswordNewSalt(sessionPassword));
        String sessionPasswordHash = sessionPassHashAndSalt[0];
        String salt = sessionPassHashAndSalt[1];
        getSessionUser().setEmailHash(sessionEmailHash);
        getSessionUser().setPasswordHash(sessionPasswordHash);
        getSessionUser().setSalt(salt);
        M_DbService.createUser(sessionEmailHash, salt);
        M_DbService.insertUserPassword(sessionPasswordHash, sessionEmailHash);
        return loginUser(sessionUser);
    }

    public static boolean loginUser(User sessionUser){
        if(!findDbUser(sessionUser)) return false;
        System.out.println("aus C_General:");
        System.out.println(C_SessionData.getSessionUser());
        System.out.println(C_SessionData.getDbUser());
        return sessionUser.equals(getDbUser());
    }
}
