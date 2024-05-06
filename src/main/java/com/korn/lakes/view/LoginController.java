package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private Hyperlink createAccount;

    @FXML
    private Button weiter;

    @FXML
    protected void onCreateAccount() {
        System.out.println("zum Registrieren");
    }
}