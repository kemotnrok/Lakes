package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class ViewController_loginPassword {

    private static String email;
    private static String password;

    @FXML
    private Button zurueckToLoginEmail;

    @FXML
    private Button weiterToLandingPage;

    @FXML
    private Node eyeLoginPassword;

    @FXML
    private PasswordField passwordField;

//    ---------- Methoden ----------

    @FXML
    protected void onZurueckToLoginEmail() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(zurueckToLoginEmail, "view-loginEmail");
        ViewController_loginEmail controller = ((ViewController_loginEmail) changeScene.getController());
        controller.setEmail(email);
        if (!(passwordField.getText() == null)) {
            ViewController_loginEmail.setPassword(passwordField.getText());
        }
    }

    @FXML
    protected void onWeiterToLandingPage() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(weiterToLandingPage, "view-landingPage");
    }

    @FXML
    protected void showPasswordLogin() {
        ViewHelper_methods.unhidePassword(eyeLoginPassword);
    }

//    ---------- Setter/Getter ----------

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
    }
}