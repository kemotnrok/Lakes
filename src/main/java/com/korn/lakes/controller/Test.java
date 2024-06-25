package com.korn.lakes.controller;

import jakarta.mail.MessagingException;

public class Test {
    public static void main(String[] args) throws MessagingException {
        C_Mail mail = new C_Mail();
        Token token = new Token();
        String adresse = "thomas.korn@gmx.at";
        mail.sendEmail(adresse, token.getValue());
    }
}
