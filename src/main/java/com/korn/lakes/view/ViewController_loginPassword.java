package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController_loginPassword implements Initializable {

    @FXML
    private Button backToLoginEmail;
    @FXML
    private Button continueToLandingPage;
    @FXML
    private Node eyeLoginPassword;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordPlain;
    @FXML
    private Button forgotPassword;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePasswordField();
        passwordField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (newFocus) return;
            if (isPasswordValid()) actionIfPasswordValid();
            else actionIfPasswordNotvalid();
        });

        passwordField.setOnKeyTyped(event-> updatePassword());
    }

    @FXML
    protected void onBackToLoginEmail() {
        ViewHelper_changeScene changeScene = new ViewHelper_changeScene(backToLoginEmail, "view-loginEmail");
    }

    @FXML
    protected void onContinueToLandingPage() {
        if (isPasswordValid()) {
            actionIfPasswordValid();
            ViewHelper_changeScene changeScene = new ViewHelper_changeScene(continueToLandingPage, "view-landingPage");
        } else actionIfPasswordNotvalid();

    }

    @FXML
    public void updatePassword() {
        ViewHelper_tempData.setPassword(passwordField.getText());
        if (isPasswordValid()) actionIfPasswordValid();
    }

    @FXML
    public void updatePasswordField() {
        passwordField.setText(ViewHelper_tempData.getPassword());
    }

    @FXML
    protected void showPasswordLogin() {
        ViewHelper_diverseMethods.showPassword(eyeLoginPassword);
        passwordPlain.requestFocus(); // todo: eigentlich kein Fokus / alle Elemente abwählen
    }

    @FXML
    private boolean isPasswordValid() {
        return ViewHelper_diverseMethods.validatePassword(passwordField.getText());
    }

    @FXML
    private void actionIfPasswordNotvalid() {
        if (!passwordField.getStyleClass().contains("warning")) {
            passwordField.requestFocus();
        }
        passwordField.getStyleClass().add("warning");
        passwordPlain.getStyleClass().add("warning");
        passwordField.selectEnd();
        continueToLandingPage.setDisable(true);
        passwordField.setOnAction(null);
    }

    @FXML
    private void actionIfPasswordValid() {
        passwordField.getStyleClass().remove("warning");
        passwordPlain.getStyleClass().remove("warning");
        passwordField.setOnAction(event -> onContinueToLandingPage());
        continueToLandingPage.setDisable(false);
    }
}
