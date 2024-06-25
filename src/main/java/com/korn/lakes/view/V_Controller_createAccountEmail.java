package com.korn.lakes.view;

import com.korn.lakes.controller.C_Mail;
import com.korn.lakes.controller.C_SessionData;
import com.korn.lakes.model.DTO.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class V_Controller_createAccountEmail implements Initializable {

    @FXML
    private Button backToLoginEmailFromCreateAccount;
    @FXML
    private Button continueToCreateAccountPassword;
    @FXML
    private TextField createAccountEmailField;
    @FXML
    private Text infoCreateEmail;

    User sessionUser;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sessionUser = C_SessionData.getSessionUser();
        updateCreateAccountEmailField();

        createAccountEmailField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (newFocus) return;
            if (isEmailValid()) actionIfEmailValid();
            else actionIfEmailNotValid();
        });

        createAccountEmailField.setOnKeyTyped(event -> updateEmail());
    }

    private void updateUser(User sessionUser) {
        C_SessionData.setSessionUser(sessionUser);
    }

    @FXML
    protected void onBackToLoginEmailFromCreateAccount() {
        updateUser(sessionUser);
        new V_changeScene(backToLoginEmailFromCreateAccount, "view-loginEmail");
    }

    @FXML
    protected void onContinueToCreateAccountPassword() {
        if (!isEmailValid()) {
            actionIfEmailNotValid();
            return;
        }
        actionIfEmailValid();
        updateUser(sessionUser);
        // Todo Tokenüberprüfung
        new V_changeScene(continueToCreateAccountPassword, "view-createAccountPassword");
    }

    @FXML
    public void updateEmail() {
        sessionUser.setEmail(createAccountEmailField.getText());
        if (isEmailValid()) actionIfEmailValid();
        clearInfo();
    }

    @FXML
    public void updateCreateAccountEmailField() {
        createAccountEmailField.setText(sessionUser.getEmail());
    }

    @FXML
    private boolean isEmailValid() {
        return V_Helper_diversMethods.validateEmail(createAccountEmailField.getText());
    }

    @FXML
    private void actionIfEmailValid() {
        createAccountEmailField.getStyleClass().remove("warning");
        // beim zweiten Mal wird die Klasse sicher entfernt
        createAccountEmailField.getStyleClass().remove("warning");
        createAccountEmailField.setOnAction(event -> onContinueToCreateAccountPassword());
        continueToCreateAccountPassword.setDisable(false);
        clearInfo();
    }

    @FXML
    private void actionIfEmailNotValid() {
        if (!createAccountEmailField.getStyleClass().contains("warning")) {
            createAccountEmailField.requestFocus();
            createAccountEmailField.selectEnd();
        }
        setInfo("Ungültige E-Mail-Adresse");
        createAccountEmailField.getStyleClass().add("warning");
        createAccountEmailField.selectEnd();
        continueToCreateAccountPassword.setDisable(true);
        createAccountEmailField.setOnAction(null);
    }

    private void verifyAccount(){
        C_Mail mail = new C_Mail();
//        mail.sendEmail(sessionUser.getEmail(), new Token().getValue()); // Todo Token dem User zuweisen
    }

    @FXML
    private void setInfo(String text) {
        infoCreateEmail.setText(text);
        infoCreateEmail.setVisible(true);
    }

    @FXML
    private void clearInfo() {
        infoCreateEmail.setText("");
        infoCreateEmail.setVisible(false);
    }
}
