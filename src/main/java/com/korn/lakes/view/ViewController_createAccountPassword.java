package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class ViewController_createAccountPassword {

    private static String email;
    private static String password;

    @FXML
    private Button weiterToConfirmAccountFromPassword;

    @FXML
    private Button zurueckToAccountEmailFromPassword;

    @FXML
    private Node eyeLoginPassword;

    @FXML
    private PasswordField passwortFieldAccount;

    @FXML
    protected void onWeiterToConfirmAccount(){
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(weiterToConfirmAccountFromPassword, "view-confirmAccount");
    }

    @FXML
    protected void onZurueckToLoginEmail() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(zurueckToAccountEmailFromPassword, "view-loginEmail");
        ((ViewController_loginEmail) changeScene.getController()).setEmail(email);
        if (!(passwortFieldAccount.getText() == null)) {
            ViewController_createAccountEmail.setPassword(passwortFieldAccount.getText());
        }
    }

    @FXML
    protected void showPasswordLogin(){
        ViewHelper_methods.unhidePassword(eyeLoginPassword);
    }

//    ---------- Setter/Getter ----------

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ViewController_createAccountPassword.email = email;
    }

    public static String getPassword() {
        return password;
    }

    @FXML
    public void setPassword(String password) {
        passwortFieldAccount.setText(password);
        ViewController_createAccountPassword.password = password;
    }
}
