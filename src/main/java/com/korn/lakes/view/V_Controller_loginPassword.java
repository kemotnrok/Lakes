package com.korn.lakes.view;

import com.korn.lakes.controller.C_SessionData;
import com.korn.lakes.model.DTO.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static com.korn.lakes.controller.C_General.checkPassword;
import static com.korn.lakes.controller.C_SessionData.getDbUser;
import static com.korn.lakes.controller.C_SessionData.getSessionUser;

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
    private Button forgotPassword; //todo

    User sessionUser;
    private boolean passwordNotMatch;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sessionUser = C_SessionData.getSessionUser();
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
            updateUser(sessionUser);
            actionIfPasswordValid();
            if (!checkPassword(sessionUser)) {
                actionIfNoMatchPassword();
                return;
            }
            new V_changeScene(continueToLandingPage, "view-landingPage");
        } else actionIfPasswordNotvalid();
        System.out.println(getSessionUser().toString()); // todo löschen
        System.out.println(getDbUser().toString()); // todo löschen
    }

    @FXML
    protected void onBackToLoginEmail() {
        updateUser(sessionUser);
        new V_changeScene(backToLoginEmail, "view-loginEmail");
    }

    @FXML
    public void updatePassword() {
        sessionUser.setPassword(passwordField.getText());
        passwordNotMatch = false;
        if (isPasswordValid()) actionIfPasswordValid();
    }

    @FXML
    public void updatePasswordField() {
        passwordNotMatch = false;
        passwordField.setText(sessionUser.getPassword());
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
        setInfo("Mindestens 8 Zeichen und mind. zwei der Elemente: Klein-, Großbuchstaben, Zahlen und Sonderzeichen.");
    }

    @FXML
    private void actionIfPasswordValid() {
        passwordField.getStyleClass().remove("warning");
        passwordPlain.getStyleClass().remove("warning");
        passwordField.setOnAction(event -> onContinueToLandingPage());
        if (!passwordNotMatch) {
            continueToLandingPage.setDisable(false);
            clearInfo();
        }
    }

    @FXML
    public void actionIfNoMatchPassword() {
        passwordNotMatch = true;
        setInfo("Kein passendes Passwort");
        continueToLandingPage.setDisable(true);
        passwordField.setOnAction(null);
        forgotPassword.requestFocus();
    }

    @FXML
    private void setInfo(String text) {
        infoLoginPassword.setText(text);
        infoLoginPassword.setVisible(true);
    }

    @FXML
    private void clearInfo() {
        infoLoginPassword.setText("");
        infoLoginPassword.setVisible(false);
    }

    private void updateUser(User sessionUser) {
        C_SessionData.setSessionUser(sessionUser);
    }
}
