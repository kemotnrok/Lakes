package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController_loginEmail implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private Button createAccount;
    @FXML
    private Button continueToLoginPassword;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateEmailField();

        emailField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (newFocus) return;
            if (isEmailValid()) actionIfEmailValid();
            else actionIfEmailNotvalid();
        });

        emailField.setOnKeyTyped(event-> updateEmail());
    }

    @FXML
    private void onCreateAccount() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(createAccount, "view-createAccountEmail");
    }

    @FXML
    private void onContinueToLoginPassword() {
        if (isEmailValid()) {
            actionIfEmailNotvalid();
            ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToLoginPassword, "view-loginPassword");
        } else actionIfEmailNotvalid();
    }

    @FXML
    private void updateEmail() {
        ViewHelper_tempData.setEmail(emailField.getText());
        if (isEmailValid()) actionIfEmailValid();
    }

    @FXML
    private void updateEmailField() {
        emailField.setText(ViewHelper_tempData.getEmail());
    }

    @FXML
    private boolean isEmailValid() {
        return ViewHelper_diverseMethods.validateEmail(emailField.getText());
    }

    @FXML
    private void actionIfEmailNotvalid() {
        if (!emailField.getStyleClass().contains("warning")) {
            emailField.requestFocus();
        }
        emailField.getStyleClass().add("warning");
        emailField.selectEnd();
        continueToLoginPassword.setDisable(true);
        emailField.setOnAction(null);
    }

    @FXML
    private void actionIfEmailValid() {
        emailField.getStyleClass().remove("warning");
        emailField.selectEnd();
        emailField.setOnAction(event -> onContinueToLoginPassword());
        continueToLoginPassword.setDisable(false);
    }
}