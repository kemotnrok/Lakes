package com.korn.lakes.view;

import com.korn.lakes.controller.C_Helper_diversMethods;
import com.korn.lakes.controller.C_Helper_tempData;
import com.korn.lakes.model.M_Databases;
import com.korn.lakes.model.M_DbService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class V_Controller_loginEmail implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private Button createAccount;
    @FXML
    private Button continueToLoginPassword;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateEmailField();

        emailField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (newFocus) return;
            if (isEmailValid()) actionIfEmailValid();
            else actionIfEmailNotvalid();
        });

        emailField.setOnKeyTyped(event-> updateEmail());
    }

    @FXML
    private void onCreateAccount() {
        V_Helper_changeScene changeScene = new V_Helper_changeScene(createAccount, "view-createAccountEmail");
    }

    @FXML
    private void onContinueToLoginPassword() {
        if (isEmailValid()) {
            actionIfEmailValid();
            V_Helper_changeScene changeScene = new V_Helper_changeScene(continueToLoginPassword, "view-loginPassword");
            M_DbService.updateDB(emailField.getText(), M_Databases.users_db);
        } else actionIfEmailNotvalid();
    }

    @FXML
    private void updateEmail() {
        C_Helper_tempData.setEmail(emailField.getText());
        if (isEmailValid()) actionIfEmailValid();
    }

    @FXML
    private void updateEmailField() {
        emailField.setText(C_Helper_tempData.getEmail());
    }

    @FXML
    private boolean isEmailValid() {
        return V_Helper_diversMethods.validateEmail(emailField.getText());
    }

    @FXML
    private void actionIfEmailNotvalid() {
        if (!emailField.getStyleClass().contains("warning")) {
            emailField.requestFocus();
        }
        emailField.getStyleClass().add("warning");
        emailField.selectEnd();
        continueToLoginPassword.setDisable(true);
        emailField.setOnAction(null);
    }

    @FXML
    private void actionIfEmailValid() {
        emailField.getStyleClass().remove("warning");
        emailField.selectEnd();
        emailField.setOnAction(event -> onContinueToLoginPassword());
        continueToLoginPassword.setDisable(false);
    }

    @FXML
    private boolean isEmailInDb(){
        return C_Helper_diversMethods.checkEmailDb(emailField.getText());
    }
}