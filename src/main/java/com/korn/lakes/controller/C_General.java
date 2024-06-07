package com.korn.lakes.controller;

import com.korn.lakes.App;
import com.korn.lakes.model.DTO.User;
import com.korn.lakes.model.M_Crypto;
import com.korn.lakes.model.M_Databases;
import com.korn.lakes.model.M_DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static com.korn.lakes.controller.C_SessionData.getDbUser;
import static com.korn.lakes.controller.C_SessionData.getSessionUser;
import static com.korn.lakes.model.M_DbService.findUser;
import static com.korn.lakes.model.M_DbService.updateUserPassword;

public class C_General {

    public static boolean develop = false;

    //    Logger
    private static final Logger logger = LoggerFactory.getLogger(App.class);

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
        if(C_General.develop) logger.info(sessionUser.toString());
        return loginUser(sessionUser);
    }

    public static boolean findDbUser(User sessionUser) {
        String sessionEmailHash = M_Crypto.hashEmail(sessionUser.getEmail());
        ArrayList<HashMap<String, String>> result = findUser(sessionEmailHash, M_Databases.users_db);
        getSessionUser().setEmailHash(sessionEmailHash);
        if (result.isEmpty()) {
            if(C_General.develop) logger.info("Kein User gefunden");
            return false;
        }
        else {
            HashMap<String, String> row = result.getFirst();
            User dbUser = getDbUser();
            dbUser.setEmailHash(row.get("e_mail_hash"));
            dbUser.setSalt(row.get("salt"));
            dbUser.setPasswordHash(row.get("password_hash"));
            if(C_General.develop) logger.info(getDbUser().toString());
            return true;
        }
    }

    public static boolean checkPassword(User sessionUser) {
        String sessionEmailHash = sessionUser.getEmailHash();
        String sessionPassword = sessionUser.getPassword();
        String salt = getDbUser().getSalt();
        String sessionPasswordHash = Objects.requireNonNull(M_Crypto.hashPasswordGivenSalt(sessionPassword, salt));
        ArrayList<HashMap<String, String>> result = M_DbService.findPassword(sessionEmailHash, M_Databases.users_db);
        if (result.isEmpty()) {
            if(C_General.develop) logger.info("Falsches Passwort");
            return false;
        }
        else {
            HashMap<String, String> row = result.getFirst();
            String dbPasswordHash = row.get("password_hash");
            getDbUser().setPasswordHash(dbPasswordHash);
            if(C_General.develop) logger.info("Eingegeben:" + sessionPasswordHash + "\nDatenbank: " + dbPasswordHash);
            return Objects.equals(dbPasswordHash, sessionPasswordHash);
        }
    }

    public static boolean changePassword(User sessionUser){
//        E-Mail-Senden simulieren
        String sessionEmailHash = sessionUser.getEmailHash();
        String sessionPassword = sessionUser.getPassword();
        String[] sessionPassHashAndSalt = Objects.requireNonNull(M_Crypto.hashPasswordNewSalt(sessionPassword));
        String sessionPasswordHash = sessionPassHashAndSalt[0];
        String salt = sessionPassHashAndSalt[1];
        getSessionUser().setPasswordHash(sessionPasswordHash);
        getSessionUser().setSalt(salt);
        updateUserPassword(sessionPasswordHash, salt, sessionEmailHash);
        return loginUser(sessionUser);
    }

    public static boolean loginUser(User sessionUser){
        if(!findDbUser(sessionUser)) return false;
        if(C_General.develop) logger.info("""
                SessionUser:{}DbUser:{}""", sessionUser, C_SessionData.getDbUser());
        return sessionUser.equals(getDbUser());
    }
}
