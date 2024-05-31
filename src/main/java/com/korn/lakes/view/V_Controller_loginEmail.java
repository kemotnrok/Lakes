package com.korn.lakes.view;

import com.korn.lakes.controller.C_Helper_diversMethods;
import com.korn.lakes.controller.C_SessionData;
import com.korn.lakes.model.DTO.User;
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

    User viewUser;
    User dbUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewUser = C_SessionData.getViewUser();
        dbUser = C_SessionData.getDbUser();
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
        updateUser(viewUser, dbUser);
        V_changeScene changeScene = new V_changeScene(createAccount, "view-createAccountEmail");
    }

    @FXML
    private void onContinueToLoginPassword() {
        if (isEmailValid()) {
            updateUser(viewUser, dbUser);
            actionIfEmailValid();
            V_changeScene changeScene = new V_changeScene(continueToLoginPassword, "view-loginPassword");
            C_SessionData.setViewUser(viewUser);
        } else actionIfEmailNotvalid();
    }

    @FXML
    private void updateEmail() {
        viewUser.setEmail(emailField.getText());
        if (isEmailValid()) actionIfEmailValid();
    }

    @FXML
    private void updateEmailField() {
        emailField.setText(viewUser.getEmail());
    }

    @FXML
    private boolean isEmailValid() {
        return V_Helper_diversMethods.validateEmail(emailField.getText());
    }

    @FXML
    private void actionIfEmailValid() {
        emailField.getStyleClass().remove("warning");
        emailField.selectEnd();
        emailField.setOnAction(event -> onContinueToLoginPassword());
        continueToLoginPassword.setDisable(false);
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

    private void updateUser(User viewUser, User dbUser){
        C_SessionData.setViewUser(viewUser);
        C_SessionData.setDbUser(dbUser);
    }

    @FXML
    private boolean isEmailInDb(){
        return C_Helper_diversMethods.checkEmailDb(emailField.getText());
    }

}