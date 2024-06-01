package com.korn.lakes.view;

import com.korn.lakes.controller.C_SessionData;
import com.korn.lakes.model.DTO.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class V_Controller_createAccountEmail implements Initializable {

    @FXML
    private Button backToLoginEmailFromCreateAccount;
    @FXML
    private Button continueToCreateAccountPassword;
    @FXML
    private TextField createAccountEmailField;

    User viewUser;
    User dbUser;


//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewUser = C_SessionData.getViewUser();
        dbUser = C_SessionData.getDbUser();

        updateCreateAccountEmailField();

        createAccountEmailField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (newFocus) return;
            if (isEmailValid()) actionIfEmailValid();
            else actionIfEmailNotvalid();
        });

        createAccountEmailField.setOnKeyTyped(event-> updateEmail());
    }

    private void updateUser(User viewUser){
        C_SessionData.setViewUser(viewUser);
    }

    @FXML
    protected void onBackToLoginEmailFromCreateAccount() {
        updateUser(viewUser);
        new V_changeScene(backToLoginEmailFromCreateAccount, "view-loginEmail");
    }

    @FXML
    protected void onContinueToCreateAccountPassword() {
        if (isEmailValid()) {
            updateUser(viewUser);
            actionIfEmailNotvalid();
            new V_changeScene(continueToCreateAccountPassword, "view-createAccountPassword");
        } else actionIfEmailNotvalid();
    }

    @FXML
    public void updateEmail() {
        viewUser.setEmail(createAccountEmailField.getText());
        if (isEmailValid()) actionIfEmailValid();
    }

    @FXML
    public void updateCreateAccountEmailField() {
        createAccountEmailField.setText(viewUser.getEmail());
    }

    @FXML
    private boolean isEmailValid() {
        return V_Helper_diversMethods.validateEmail(createAccountEmailField.getText());
    }

    @FXML
    private void actionIfEmailNotvalid() {
        if (!createAccountEmailField.getStyleClass().contains("warning")) {
            createAccountEmailField.requestFocus();
        }
        createAccountEmailField.getStyleClass().add("warning");
        createAccountEmailField.selectEnd();
        continueToCreateAccountPassword.setDisable(true);
        createAccountEmailField.setOnAction(null);
    }

    @FXML
    private void actionIfEmailValid() {
        createAccountEmailField.getStyleClass().remove("warning");
        createAccountEmailField.setOnAction(event -> onContinueToCreateAccountPassword());
        continueToCreateAccountPassword.setDisable(false);
    }
}
