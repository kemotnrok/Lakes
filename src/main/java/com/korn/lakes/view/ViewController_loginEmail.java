package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class ViewController_loginEmail {
    private static String email;
    private static String password;

    @FXML
    private TextField emailField;

    @FXML
    private Hyperlink createAccount;

    @FXML
    private Button weiterToLoginPassword;

//    ---------- Methoden ----------

    @FXML
    protected void onCreateAccount() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(createAccount, "view-createAccountEmail");
        ((ViewController_createAccountEmail) changeScene.getController()).setEmail(emailField.getText());
        if(!(password == null)){
            ViewController_createAccountEmail.setPassword(password);
        }
    }

    @FXML
    protected void onWeiterToLoginPassword() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(weiterToLoginPassword, "view-loginPassword");
        ViewController_loginPassword.setEmail(emailField.getText());
        if(!(password == null)){
            ((ViewController_loginPassword) changeScene.getController()).setPassword(password);
        }
    }

    /**
     * Transfer email to the next dialogue box
     * @param email String
     */
    protected void setEmail(String email){
        ViewController_loginEmail.email = email;
        emailField.setText(email);
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ViewController_loginEmail.password = password;
    }
}