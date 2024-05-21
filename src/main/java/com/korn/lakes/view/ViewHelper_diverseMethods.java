package com.korn.lakes.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class ViewHelper_diverseMethods {

    /**
     * Makes Password visible
     * @param fxElement Node
     */
    protected static void unhidePassword(Node fxElement) {
        try {
            Parent parent = fxElement.getParent();

            PasswordField passwordField = null;
            TextField textField = null;

            for (Node n : parent.getChildrenUnmodifiable()) {
                if (!(n instanceof PasswordField) && n instanceof TextField) {
                    textField = (TextField) n;
                }
                if (n instanceof PasswordField) {
                    passwordField = (PasswordField) n;
                    break;
                }
            }
            assert textField != null;
            assert passwordField != null;
            textField.textProperty().bind(passwordField.textProperty());
            passwordField.setVisible(!passwordField.isVisible());
            passwordField.requestFocus();
            passwordField.selectEnd();

        } catch (Exception e) {
            System.out.println("Passwort-Anzeige-Fehler"); //todo
        }

    }

    /**
     * Checks if email is valid
     * @param email String
     * @return boolean
     */
    protected static boolean validateEmail(String email){
        final String EMAIL_REGEX = "^[a-z0-9_+&*-.]+@([a-z0-9-]+\\.)+[a-z]{2,3}$";
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        return emailPattern.matcher(email).matches();
    }
    // Todo Apache-Bibliothek einbinden

    /**
     * Checks if password is valid
     * @param password String
     * @return boolean
     */
    protected boolean validatePassword(String password){
        return true;

    }
    // Todo
}
