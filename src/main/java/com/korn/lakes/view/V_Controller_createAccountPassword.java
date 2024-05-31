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
    private Text infoCreateAccountPassword;

    User viewUser;
    User dbUser;



//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewUser = C_SessionData.getViewUser();
        dbUser = C_SessionData.getDbUser();
        updateCreateAccountPasswordField();
        createAccountPasswordField.focusedProperty().addListener((observable, oldFocus, newFocus) -> {
            if (newFocus) return;
            if (isPasswordValid()) actionIfPasswordValid();
            else actionIfPasswordNotvalid();
        });
        createAccountPasswordField.setOnKeyTyped(event-> updatePassword());
    }

    //    ----------

    private void updateUser(User viewUser) {
        C_SessionData.setViewUser(viewUser);
    }

    @FXML
    protected void onContinueToConfirmAccount(){
        if (isPasswordValid()) {
            updateUser(viewUser);
            actionIfPasswordValid();
            V_changeScene changeScene = new V_changeScene(continueToConfirmAccountFromPassword, "view-confirmAccount");
        } else actionIfPasswordNotvalid();
    }

    @FXML
    protected void onBackToCreateAccountEmail() {
        updateUser(viewUser);
        V_changeScene changeScene = new V_changeScene(backToAccountEmailFromPassword, "view-createAccountEmail");
    }

    @FXML
    public void updatePassword() {
        viewUser.setPassword(createAccountPasswordField.getText());
        if (isPasswordValid()) actionIfPasswordValid();
    }

    @FXML
    public void updateCreateAccountPasswordField() {
        createAccountPasswordField.setText(viewUser.getPassword());
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

    @FXML
    private void writeEmail(){
//    todo?
    }
}
