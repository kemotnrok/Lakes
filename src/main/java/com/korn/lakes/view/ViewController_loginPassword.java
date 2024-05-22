package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController_loginPassword implements Initializable {

    @FXML
    private Button backToLoginEmail;
    @FXML
    private Button continueToLandingPage;
    @FXML
    private Node eyeLoginPassword;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button forgotPassword;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePasswordField();
    }

    @FXML
    protected void onBackToLoginEmail() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(backToLoginEmail, "view-loginEmail");
    }

    @FXML
    protected void onContinueToLandingPage() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToLandingPage, "view-landingPage");
    }

    @FXML
    public void updatePassword() {
        ViewHelper_tempData.setPassword(passwordField.getText());
    }

    @FXML
    public void updatePasswordField() {
        passwordField.setText(ViewHelper_tempData.getPassword());
    }

    @FXML
    protected void showPasswordLogin() {
        ViewHelper_diverseMethods.unhidePassword(eyeLoginPassword);
    }
}