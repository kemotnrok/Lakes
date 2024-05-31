package com.korn.lakes.exceptions;

import com.korn.lakes.model.DTO.User;

public class NoUserFoundException extends Exception{
    public NoUserFoundException(User user){
        super("Kein User unter " + user.getEmail() + " gefunden.");
    }

}
