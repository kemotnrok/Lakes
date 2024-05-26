package com.korn.lakes.view;

import com.korn.lakes.controller.C_Helper_tempData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class V_Controller_loginPassword implements Initializable {

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
    private Text infoLoginPassword;
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
    protected void onContinueToLandingPage() {
        if (isPasswordValid()) {
            actionIfPasswordValid();
            V_Helper_changeScene changeScene = new V_Helper_changeScene(continueToLandingPage, "view-landingPage");
        } else actionIfPasswordNotvalid();
    }

    @FXML
    protected void onBackToLoginEmail() {
        V_Helper_changeScene changeScene = new V_Helper_changeScene(backToLoginEmail, "view-loginEmail");
    }

    @FXML
    public void updatePassword() {
        C_Helper_tempData.setPassword(passwordField.getText());
        if (isPasswordValid()) actionIfPasswordValid();
    }

    @FXML
    public void updatePasswordField() {
        passwordField.setText(C_Helper_tempData.getPassword());
    }

    @FXML
    protected void showPasswordLogin() {
        V_Helper_diversMethods.showPassword(eyeLoginPassword);
        passwordPlain.requestFocus(); // todo: eigentlich kein Fokus / alle Elemente abwählen
    }

    @FXML
    private boolean isPasswordValid() {
        return V_Helper_diversMethods.validatePassword(passwordField.getText());
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
        infoLoginPassword.setVisible(true);
    }

    @FXML
    private void actionIfPasswordValid() {
        passwordField.getStyleClass().remove("warning");
        passwordPlain.getStyleClass().remove("warning");
        passwordField.setOnAction(event -> onContinueToLandingPage());
        continueToLandingPage.setDisable(false);
        infoLoginPassword.setVisible(false);
    }
}
