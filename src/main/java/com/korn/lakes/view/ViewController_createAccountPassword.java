package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class ViewController_createAccountPassword {

    private static String email;
    private static String password;

    @FXML
    private Button continueToConfirmAccountFromPassword;

    @FXML
    private Button backToAccountEmailFromPassword;

    @FXML
    private Node eyeLoginPassword;

    @FXML
    private PasswordField passwortFieldAccount;

    @FXML
    protected void onContinueToConfirmAccount(){
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToConfirmAccountFromPassword, "view-confirmAccount");
    }

    @FXML
    protected void onBackToLoginEmail() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(backToAccountEmailFromPassword, "view-loginEmail");
        ((ViewController_loginEmail) changeScene.getController()).updateEmailField(email);
        if (!(passwortFieldAccount.getText() == null)) {
            ViewController_createAccountEmail.setPassword(passwortFieldAccount.getText());
        }
    }

    @FXML
    protected void showPasswordLogin(){
        ViewHelper_diverseMethods.unhidePassword(eyeLoginPassword);
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
