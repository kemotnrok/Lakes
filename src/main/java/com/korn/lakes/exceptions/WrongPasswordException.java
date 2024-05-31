package com.korn.lakes.exceptions;

import com.korn.lakes.model.DTO.User;

public class WrongPasswordException extends Exception{
    public WrongPasswordException(User user) {
        super("Falsches Passwort für " + user.getEmail() + ".");
    }
}
