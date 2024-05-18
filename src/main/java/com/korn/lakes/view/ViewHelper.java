package com.korn.lakes.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ViewHelper {

    protected static void unhidePassword(Node element) {
        try {
            Parent parent = element.getParent();

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
            return;
        }

    }
}
