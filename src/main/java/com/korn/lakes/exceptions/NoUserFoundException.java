package com.korn.lakes.exceptions;

import com.korn.lakes.model.DTO.User;

public class NoUserFoundException extends Exception {
    public NoUserFoundException() {
        super("Benutzer nicht gefunden");
    }

}
