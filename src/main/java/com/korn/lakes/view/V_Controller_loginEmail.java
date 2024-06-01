package com.korn.lakes.view;

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

        emailField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (newFocus) return;
            if (isEmailValid()) actionIfEmailValid();
            else actionIfEmailNotvalid();
        });

        emailField.setOnKeyTyped(event-> updateEmail());
    }

    @FXML
    private void onCreateAccount() {
        updateUser(sessionUser);
        new V_changeScene(createAccount, "view-createAccountEmail");
    }

    @FXML
    private void onContinueToLoginPassword() throws NoUserFoundException {
        if (!isEmailValid()) {
            actionIfEmailNotvalid();
            return;
        }
        actionIfEmailValid();
        updateUser(sessionUser);
        if (!findDbUser(sessionUser)) {
            actionIfUserNotFound();
            return;
        }
        new V_changeScene(continueToLoginPassword, "view-loginPassword");
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
        emailField.selectEnd();
        emailField.setOnAction(event -> {
            try {
                onContinueToLoginPassword();
            } catch (NoUserFoundException e) {
                throw new RuntimeException(e);
            }
        });
        if (!userNotFound){
        continueToLoginPassword.setDisable(false);
        clearInfo();
        }
    }

    @FXML
    private void actionIfEmailNotvalid() {
        if (!emailField.getStyleClass().contains("warning")) emailField.requestFocus();
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
    private void setInfo(String text){
        infoLoginEmail.setText(text);
        infoLoginEmail.setVisible(true);
    }

    @FXML
    private void clearInfo(){
        infoLoginEmail.setText("");
        infoLoginEmail.setVisible(false);
    }

    private void updateUser(User sessionUser) {
        setSessionUser(sessionUser);
    }
}