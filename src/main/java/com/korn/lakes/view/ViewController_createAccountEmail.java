package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController_createAccountEmail implements Initializable {

    @FXML
    private Button backToLoginEmailFromCreateAccount;
    @FXML
    private Button continueToCreateAccountPassword;
    @FXML
    private TextField createAccountEmailField;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateCreateAccountEmailField(ViewHelper_tempData.getEmail());

        createAccountEmailField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (newFocus) return;
            if (isEmailValid()) actionIfEmailValid();
            else actionIfEmailNotvalid();
        });

        createAccountEmailField.setOnKeyTyped(event-> updateEmail());
    }

    @FXML
    protected void onBackToLoginEmailFromCreateAccount() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(backToLoginEmailFromCreateAccount, "view-loginEmail");
    }

    @FXML
    protected void onContinueToCreateAccountPassword() {
        if (isEmailValid()) {
            actionIfEmailNotvalid();
            ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToCreateAccountPassword, "view-createAccountPassword");
        } else actionIfEmailNotvalid();
    }

    @FXML
    public void updateEmail() {
        ViewHelper_tempData.setEmail(createAccountEmailField.getText());
        if (isEmailValid()) actionIfEmailValid();
    }

    @FXML
    public void updateCreateAccountEmailField(String text) {
        createAccountEmailField.setText(ViewHelper_tempData.getEmail());
    }

    @FXML
    private boolean isEmailValid() {
        return ViewHelper_diverseMethods.validateEmail(createAccountEmailField.getText());
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
