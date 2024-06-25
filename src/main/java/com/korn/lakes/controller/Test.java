package com.korn.lakes.controller;

public class Test {
    public static void main(String[] args) {
        C_Mail mail = new C_Mail();
        Token token = new Token();
        token.getValue();
        mail.sendEmail("thomas.korn@gmx.at",  token.getValue());
    }
}
