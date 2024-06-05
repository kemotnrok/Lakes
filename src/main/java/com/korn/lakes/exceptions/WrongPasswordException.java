package com.korn.lakes.exceptions;

import com.korn.lakes.model.DTO.User;
import com.korn.lakes.view.V_Controller_loginPassword;

public class WrongPasswordException extends Exception{
    public WrongPasswordException() {
        super ("Kein passendes Passwort");
    }
}
