package com.korn.lakes.exceptions;

public class WrongPasswordException extends Exception{
    public WrongPasswordException() {
        super ("Kein passendes Passwort");
    }
}
