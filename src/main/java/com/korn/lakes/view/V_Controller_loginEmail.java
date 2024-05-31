package com.korn.lakes.view;

import com.korn.lakes.exceptions.NoUserFoundException;
import com.korn.lakes.model.DTO.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.korn.lakes.controller.C_General.findDbUser;
import static com.korn.lakes.controller.C_SessionData.*;
import static com.korn.lakes.view.V_Helper_diversMethods.validateEmail;

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
        viewUser = getViewUser();
        dbUser = getDbUser();
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
    private void onContinueToLoginPassword() throws NoUserFoundException {
        if (isEmailValid()) {
            actionIfEmailValid();
            if (findDbUser(viewUser)) {
                updateUser(viewUser, dbUser);
                V_changeScene changeScene = new V_changeScene(continueToLoginPassword, "view-loginPassword");
            }
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
        return validateEmail(emailField.getText());
    }

    @FXML
    private void actionIfEmailValid() {
        emailField.getStyleClass().remove("warning");
        emailField.selectEnd();
        emailField.setOnAction(event -> {
            try {
                onContinueToLoginPassword();
            } catch (NoUserFoundException e) {
                throw new RuntimeException(e);
            }
        });
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
        setViewUser(viewUser);
        setDbUser(dbUser);
    }
}