package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ViewController_createAccountEmail {
    private static String email;
    private static String password;

    @FXML
    private Button backToLoginEmailFromCreateAccount;

    @FXML
    private Button continueToCreateAccountPassword;

    @FXML
    private TextField createAccountEmail;

    @FXML
    protected void onBackToLoginEmailFromCreateAccount() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(backToLoginEmailFromCreateAccount, "view-loginEmail");
        ((ViewController_loginEmail) changeScene.getController()).updateEmailField(createAccountEmail.getText());
        if(!(password == null)){
            ViewController_loginEmail.setPassword(password);
        }
    }

    @FXML
    protected void onContinueToCreateAccountPassword() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToCreateAccountPassword, "view-createAccountPassword");
        ViewController_createAccountPassword.setEmail(createAccountEmail.getText());
        if(!(password == null)){
            ((ViewController_createAccountPassword) changeScene.getController()).setPassword(password);
        }
    }

    /**
     * Transfer email to the previous dialogue box
     * @param emailText String
     */
    protected void setEmail(String emailText){
        createAccountEmail.setText(emailText);
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ViewController_createAccountEmail.password = password;
    }
}
