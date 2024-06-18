package com.korn.lakes.view;

import com.korn.lakes.controller.C_General;
import com.korn.lakes.exceptions.NoUserFoundException;
import com.korn.lakes.model.DTO.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static com.korn.lakes.controller.C_General.findDbUser;
import static com.korn.lakes.controller.C_SessionData.getSessionUser;
import static com.korn.lakes.controller.C_SessionData.setSessionUser;
import static com.korn.lakes.view.V_Helper_diversMethods.validateEmail;

public class V_Controller_loginEmail implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private Button createAccount;
    @FXML
    private Button continueToLoginPassword;
    @FXML
    private Text infoLoginEmail;

    private boolean userNotFound;

//    --------------------

    User sessionUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sessionUser = getSessionUser();
        updateEmailField();
        updateEmail();
        if (C_General.develop) emailField.setText("aa@aa.aa");

        emailField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (newFocus) return;
            if (isEmailValid()) actionIfEmailValid();
            else actionIfEmailNotValid();
        });

        emailField.setOnKeyTyped(event -> updateEmail());
    }

    @FXML
    private void onCreateAccount() {
        updateUser(sessionUser);
        new V_changeScene(createAccount, "view-createAccountEmail");
    }

    @FXML
    private void onContinueToLoginPassword() {
        if (!isEmailValid()) {
            actionIfEmailNotValid();
            return;
        }
        actionIfEmailValid();
        updateUser(sessionUser);

        try {
            if (findDbUser(sessionUser)) {
                new V_changeScene(continueToLoginPassword, "view-loginPassword");
            } else {
                actionIfUserNotFound();
                throw new NoUserFoundException();
            }
        } catch (NoUserFoundException e) {
            setInfo(e.getMessage());
        }
    }

    @FXML
    private void updateEmail() {
        sessionUser.setEmail(emailField.getText());
        userNotFound = false;
        if (isEmailValid()) actionIfEmailValid();
    }

    @FXML
    private void updateEmailField() {
        userNotFound = false;
        emailField.setText(sessionUser.getEmail());
        clearInfo();
    }

    @FXML
    private boolean isEmailValid() {
        return validateEmail(emailField.getText());
    }

    @FXML
    private void actionIfEmailValid() {
        emailField.getStyleClass().remove("warning");
        // beim zweiten Mal wird die Klasse sicher entfernt
        emailField.getStyleClass().remove("warning");
        emailField.selectEnd();
        emailField.setOnAction(event -> onContinueToLoginPassword());
        if (!userNotFound) {
            continueToLoginPassword.setDisable(false);
            clearInfo();
        }
    }

    @FXML
    private void actionIfEmailNotValid() {
        if (!emailField.getStyleClass().contains("warning")) {
            emailField.requestFocus();
            emailField.selectEnd();
        }
        emailField.getStyleClass().add("warning");
        emailField.selectEnd();
        continueToLoginPassword.setDisable(true);
        setInfo("Ungültige E-Mail-Adresse");
        emailField.setOnAction(null);
    }

    @FXML
    private void actionIfUserNotFound() {
        userNotFound = true;
        setInfo("Unbekannter Benutzer");
        continueToLoginPassword.setDisable(true);
        emailField.setOnAction(null);
        createAccount.requestFocus();
    }

    @FXML
    private void setInfo(String text) {
        infoLoginEmail.setText(text);
        infoLoginEmail.setVisible(true);
    }

    @FXML
    private void clearInfo() {
        infoLoginEmail.setText("");
        infoLoginEmail.setVisible(false);
    }

    private void updateUser(User sessionUser) {
        setSessionUser(sessionUser);
    }
}