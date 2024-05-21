package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController_createAccountEmail implements Initializable {

    @FXML
    private Button backToLoginEmailFromCreateAccount;
    @FXML
    private Button continueToCreateAccountPassword;
    @FXML
    private TextField createAccountEmailField;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCreateAccountEmailField(ViewHelper_tempData.getEmail());
    }

    @FXML
    protected void onBackToLoginEmailFromCreateAccount() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(backToLoginEmailFromCreateAccount, "view-loginEmail");
    }

    @FXML
    protected void onContinueToCreateAccountPassword() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToCreateAccountPassword, "view-createAccountPassword");
    }

    @FXML
    public void updateEmail() {
        ViewHelper_tempData.setEmail(createAccountEmailField.getText());
    }

    @FXML
    public void updateCreateAccountEmailField(String text) {
        createAccountEmailField.setText(ViewHelper_tempData.getEmail());
    }
}
