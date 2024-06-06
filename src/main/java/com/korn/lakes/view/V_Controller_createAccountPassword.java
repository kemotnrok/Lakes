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

import static com.korn.lakes.controller.C_General.createUser;

public class V_Controller_createAccountPassword implements Initializable {

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
    private Text infoCreatePassword;

    User sessionUser;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sessionUser = C_SessionData.getSessionUser();
        updateCreateAccountPasswordField();
        createAccountPasswordField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (newFocus) return;
            if (isPasswordValid()) actionIfPasswordValid();
            else actionIfPasswordNotvalid();
        });
        createAccountPasswordField.setOnKeyTyped(event-> updatePassword());
    }

    //    ----------

    private void updateUser(User sessionUser) {
        C_SessionData.setSessionUser(sessionUser);
    }

    @FXML
    protected void onContinueToConfirmAccount(){
        if (!isPasswordValid()) {
            actionIfPasswordNotvalid();
            return;
        }
        actionIfPasswordValid();
        updateUser(sessionUser);
        if (!createUser(sessionUser)) return;
        new V_changeScene(continueToConfirmAccountFromPassword, "view-confirmAccount");
    }

    @FXML
    protected void onBackToCreateAccountEmail() {
        updateUser(sessionUser);
        new V_changeScene(backToAccountEmailFromPassword, "view-createAccountEmail");
    }

    @FXML
    public void updatePassword() {
        sessionUser.setPassword(createAccountPasswordField.getText());
        if (isPasswordValid()) actionIfPasswordValid();
    }

    @FXML
    public void updateCreateAccountPasswordField() {
        createAccountPasswordField.setText(sessionUser.getPassword());
    }

    @FXML
    protected void showPasswordLogin(){
        V_Helper_diversMethods.showPassword(eyeLoginPassword);
    }

    @FXML
    private boolean isPasswordValid() {
        return V_Helper_diversMethods.validatePassword(createAccountPasswordField.getText());
    }

    @FXML
    private void actionIfPasswordNotvalid() {
        if (!createAccountPasswordField.getStyleClass().contains("warning")) {
            createAccountPasswordField.requestFocus();
            createAccountPasswordField.selectEnd();
        }
        createAccountPasswordField.getStyleClass().add("warning");
        passwordPlainCreateAccount.getStyleClass().add("warning");
        createAccountPasswordField.selectEnd();
        continueToConfirmAccountFromPassword.setDisable(true);
        createAccountPasswordField.setOnAction(null);
        infoCreatePassword.setText("Mindestens 8 Zeichen und mind. zwei der Elemente: Klein-, Großbuchstaben, Zahlen und Sonderzeichen.");
        infoCreatePassword.setVisible(true);
    }

    @FXML
    private void actionIfPasswordValid() {
        createAccountPasswordField.getStyleClass().remove("warning");
        passwordPlainCreateAccount.getStyleClass().remove("warning");
        // erst beim zweiten Mal wird die Klasse sicher entfernt
        createAccountPasswordField.getStyleClass().remove("warning");
        passwordPlainCreateAccount.getStyleClass().remove("warning");

        createAccountPasswordField.setOnAction(event -> onContinueToConfirmAccount());
        continueToConfirmAccountFromPassword.setDisable(false);
        infoCreatePassword.setText("");
        infoCreatePassword.setVisible(false);
    }
}
