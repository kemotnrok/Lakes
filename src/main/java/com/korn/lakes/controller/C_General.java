package com.korn.lakes.controller;

import com.korn.lakes.model.DTO.User;
import com.korn.lakes.model.M_Crypto;
import com.korn.lakes.model.M_Databases;

import java.util.ArrayList;
import java.util.HashMap;

import static com.korn.lakes.model.M_DbService.findUser;

public class C_General {

    public static boolean findDbUser(User viewUser) {
        String viewUserEmail = viewUser.getEmail();
        String viewUserEmailHash = M_Crypto.hashEmail(viewUserEmail);
        System.out.println(viewUserEmailHash);
        ArrayList<HashMap<String, String>> result = findUser(viewUserEmailHash, M_Databases.users_db);
        C_SessionData.getViewUser().setEmailHash(viewUserEmailHash);

        if (!result.isEmpty()) {
            HashMap<String, String> row = result.getFirst();
            String emailHash = row.get("e_mail_hash");
            String salt = row.get("salt");
            String passwordHash = row.get("password_hash");
            System.out.println(emailHash + " " + salt + " " + passwordHash);
            return true;
        } else {
            return false;
        }
    }
}
