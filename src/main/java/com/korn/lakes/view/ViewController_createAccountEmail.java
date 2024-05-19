package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ViewController_createAccountEmail {
    private static String email;
    private static String password;

    @FXML
    private Button zurueckToLoginEmailFromCreateAccount;

    @FXML
    private Button weiterToCreateAccountPassword;

    @FXML
    private TextField createAccountEmail;

    @FXML
    protected void onZurueckToLoginEmailFromCreateAccount() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(zurueckToLoginEmailFromCreateAccount, "view-loginEmail");
        ((ViewController_loginEmail) changeScene.getController()).setEmail(createAccountEmail.getText());
        if(!(password == null)){
            ((ViewController_createAccountPassword) changeScene.getController()).setPassword(password);
        }
    }

    @FXML
    protected void onWeiterToCreateAccountPassword() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(weiterToCreateAccountPassword, "view-createAccountPassword");
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
