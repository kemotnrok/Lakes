package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ViewController_loginEmail {
    private static String email;
    private static String password;

//    ---------- FX-elements ----------
    @FXML
    private TextField emailField;

    @FXML
    private Hyperlink createAccount;

    @FXML
    private Button continueToLoginPassword;

    @FXML
    private static Text infoText;

//    ---------- methods ----------

    @FXML
    protected void onCreateAccount() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(createAccount, "view-createAccountEmail");
        ((ViewController_createAccountEmail) changeScene.getController()).setEmail(emailField.getText());
        if (!(password == null)) {
            ViewController_createAccountEmail.setPassword(password);
        }
    }

    @FXML
    protected void onContinueToLoginPassword() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToLoginPassword, "view-loginPassword");
        ViewController_loginPassword.setEmail(emailField.getText());
        if (!(password == null)) {
            ((ViewController_loginPassword) changeScene.getController()).setPassword(password);
        }
    }

    @FXML
    public void updateEmail() {
        setEmail(emailField.getText());
    }

    @FXML
    public void updateEmailField(String text) {
        emailField.setText(text);
        updateEmail();
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

//    ---------- getter/setter ----------

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        ViewController_loginEmail.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ViewController_loginEmail.password = password;
    }
}