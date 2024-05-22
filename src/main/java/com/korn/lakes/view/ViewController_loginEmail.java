package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController_loginEmail implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private Button createAccount;
    @FXML
    private Button continueToLoginPassword;
    @FXML
    private Text infoField;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateEmailField();
        emailField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (!newFocus) {
                validateEmailOnFocusLeft();
            }
        });

        //        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                button.fire();
//            }
//        }); todo weil auf dem Mac die Leertaste die Buttons auslöst
    }

    @FXML
    private void onCreateAccount() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(createAccount, "view-createAccountEmail");
    }

    @FXML
    private void onContinueToLoginPassword() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToLoginPassword, "view-loginPassword");
    }

    @FXML
    private void updateEmail() {
        ViewHelper_tempData.setEmail(emailField.getText());
        if (ViewHelper_diverseMethods.validateEmail(emailField.getText())) {
            resetEmailFields();}
    }

    @FXML
    private void updateEmailField() {
        emailField.setText(ViewHelper_tempData.getEmail());
    }

    @FXML
    private void validateEmailOnFocusLeft() {
        if( ! ViewHelper_diverseMethods.validateEmail(emailField.getText())){
            emailField.getStyleClass().add("warning");
            infoField.setText("Geben Sie eine gültige E-Mail ein:");
            emailField.requestFocus();
            emailField.selectEnd();
        } else resetEmailFields();
    }

    @FXML
    private void resetEmailFields() {
        emailField.getStyleClass().remove("warning");
        infoField.setText("E-Mail");
    }
}