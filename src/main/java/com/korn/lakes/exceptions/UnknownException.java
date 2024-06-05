package com.korn.lakes.exceptions;

public class UnknownException extends Exception{
    public UnknownException() {
        super ("""
                Unbekannter Fehler,
                bitte versuchen Sie es noch einmal.
                """);
    }
}
