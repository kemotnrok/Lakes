package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class ViewController_loginPassword {

    private static String email;
    private static String password;

    @FXML
    private Button backToLoginEmail;

    @FXML
    private Button continueToLandingPage;

    @FXML
    private Node eyeLoginPassword;

    @FXML
    private PasswordField passwordField;

//    ---------- methods ----------

    @FXML
    protected void onBackToLoginEmail() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(backToLoginEmail, "view-loginEmail");
        ViewController_loginEmail controller = changeScene.getController();
        controller.updateEmailField(email);
        if (!(passwordField.getText() == null)) {
            ViewController_loginEmail.setPassword(passwordField.getText());
        }
        System.out.println(ViewController_loginEmail.getEmail()); // todo

    }

    @FXML
    protected void onContinueToLandingPage() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToLandingPage, "view-landingPage");
    }

    @FXML
    protected void showPasswordLogin() {
        ViewHelper_diverseMethods.unhidePassword(eyeLoginPassword);
    }

//    ---------- setter/getter ----------

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ViewController_loginPassword.email = email;
    }

    public static String getPassword() {
        return password;
    }

    @FXML
    public void setPassword(String password) {
        passwordField.setText(password);
        ViewController_loginPassword.password = password;
        ViewHelper_tempData.setPassword(password);
    }
}