package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController_createAccountPassword implements Initializable {

    @FXML
    private Button continueToConfirmAccountFromPassword;
    @FXML
    private Button backToAccountEmailFromPassword;
    @FXML
    private Node eyeLoginPassword;
    @FXML
    private PasswordField createAccountPasswordField;
    @FXML
    private TextField passwordPlainCreateAccount;
    @FXML
    private Text infoCreateAccountPassword;


//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCreateAccountPasswordField();
        createAccountPasswordField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (newFocus) return;
            if (isPasswordValid()) actionIfPasswordValid();
            else actionIfPasswordNotvalid();
        });
        createAccountPasswordField.setOnKeyTyped(event-> updatePassword());
    }

    @FXML
    protected void onContinueToConfirmAccount(){
        if (isPasswordValid()) {
            actionIfPasswordValid();
            ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToConfirmAccountFromPassword, "view-confirmAccount");
        } else actionIfPasswordNotvalid();
    }

    @FXML
    protected void onBackToCreateAccountEmail() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(backToAccountEmailFromPassword, "view-createAccountEmail");
    }

    @FXML
    public void updatePassword() {
        ViewHelper_tempData.setPassword(createAccountPasswordField.getText());
        if (isPasswordValid()) actionIfPasswordValid();
    }

    @FXML
    public void updateCreateAccountPasswordField() {
        createAccountPasswordField.setText(ViewHelper_tempData.getPassword());
    }

    @FXML
    protected void showPasswordLogin(){
        ViewHelper_diverseMethods.showPassword(eyeLoginPassword);
    }

    @FXML
    private boolean isPasswordValid() {
        return ViewHelper_diverseMethods.validatePassword(createAccountPasswordField.getText());
    }

    @FXML
    private void actionIfPasswordNotvalid() {
        if (!createAccountPasswordField.getStyleClass().contains("warning")) {
            createAccountPasswordField.requestFocus();
        }
        createAccountPasswordField.getStyleClass().add("warning");
        passwordPlainCreateAccount.getStyleClass().add("warning");
        createAccountPasswordField.selectEnd();
        continueToConfirmAccountFromPassword.setDisable(true);
        createAccountPasswordField.setOnAction(null);
        infoCreateAccountPassword.setVisible(true);
    }

    @FXML
    private void actionIfPasswordValid() {
        createAccountPasswordField.getStyleClass().remove("warning");
        passwordPlainCreateAccount.getStyleClass().remove("warning");
        createAccountPasswordField.setOnAction(event -> onContinueToConfirmAccount());
        continueToConfirmAccountFromPassword.setDisable(false);
        infoCreateAccountPassword.setVisible(false);
    }
}
