package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController_createAccountPassword implements Initializable {

    @FXML
    private Button continueToConfirmAccountFromPassword;
    @FXML
    private Button backToAccountEmailFromPassword;
    @FXML
    private Node eyeLoginPassword;
    @FXML
    private PasswordField createAccountPasswordField;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCreateAccountPasswordField();
    }

    @FXML
    public void updatePassword() {
        ViewHelper_tempData.setPassword(createAccountPasswordField.getText());
    }

    @FXML
    public void updateCreateAccountPasswordField() {
        createAccountPasswordField.setText(ViewHelper_tempData.getPassword());
    }

    @FXML
    protected void onContinueToConfirmAccount(){
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToConfirmAccountFromPassword, "view-confirmAccount");
    }

    @FXML
    protected void onBackToCreateAccountEmail() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(backToAccountEmailFromPassword, "view-createAccountEmail");
    }

    @FXML
    protected void showPasswordLogin(){
        ViewHelper_diverseMethods.showPassword(eyeLoginPassword);
    }

}
