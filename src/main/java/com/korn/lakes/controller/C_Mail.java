package com.korn.lakes.controller;

import com.korn.lakes.view.V_Controller_createAccountPassword;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class C_Mail {

    private static final String from = "thomas.korn@gmx.at";
    private final String password = "Se61Ro50Li";
    private static final String host = "mail.gmx.net";
    private static final String subject = "Your Verification Code";

//    ----------

    private static String getEmailText(String tokenString){
        return String.format("""
            Dear Customer,
                        
            Thank you for registering with us. To complete your registration, please enter the following verification code in the login screen:
                        
            **%s**
                        
            This code is valid for 10 minutes from the time you received this email.          
            If you did not request this verification code, please ignore this email or contact our support team.
                        
            Thank you,
            Lakes to See""", tokenString);
    }

    public void sendEmail(String userAdresse, String tokenString) {

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");

        Authenticator auth = new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getDefaultInstance(properties, auth);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userAdresse));
            message.setSubject(subject);
            message.setText(getEmailText(tokenString));
            Transport.send(message);
            V_Controller_createAccountPassword.setInfo("Message sent succesfully!");


        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
