package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController_loginEmail implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private Hyperlink createAccount;
    @FXML
    private Button continueToLoginPassword;
    @FXML
    private static Text infoText;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateEmailField();
    }

    @FXML
    protected void onCreateAccount() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(createAccount, "view-createAccountEmail");
    }

    @FXML
    protected void onContinueToLoginPassword() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToLoginPassword, "view-loginPassword");
    }

    @FXML
    public void updateEmail() {
        ViewHelper_tempData.setEmail(emailField.getText());
    }

    @FXML
    public void updateEmailField() {
        emailField.setText(ViewHelper_tempData.getEmail());
    }
    
    @FXML
    public void onFocusOnNext(){
        validateEmail();
        continueToLoginPassword.requestFocus();
    }

    @FXML
    public void validateEmail() {
        if( ! ViewHelper_diverseMethods.validateEmail(emailField.getText())){
            infoText.getStyleClass().add("warning");
            System.out.println(ViewController_loginEmail.infoText.getText());
            ViewController_loginEmail.infoText.setText("Bitte geben Sie eine gültige E-Mail ein");
            System.out.println(ViewController_loginEmail.infoText.getText());
        } else {
            emailField.getStyleClass().remove("my-text-field");
        }
    }
}