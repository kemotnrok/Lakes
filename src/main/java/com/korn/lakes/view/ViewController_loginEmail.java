package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ViewController_loginEmail {

    @FXML
    private TextField emailField;

    @FXML
    private Hyperlink createAccount;

    @FXML
    private Button weiterToLoginPassword;

//    ---------- Methoden ----------

    @FXML
    protected void onCreateAccount() {
        StageComponent stageComponent = new StageComponent(createAccount, "view-createAccountEmail");
        Stage nextStage = stageComponent.getStage();
        nextStage.setTitle("Lakes to see");
        ((ViewController_createAccountEmail) stageComponent.getController()).setEmail(emailField.getText());
        stageComponent.changeScene();
    }

    @FXML
    protected void onWeiterToLoginPassword() {
        StageComponent stageComponent = new StageComponent(weiterToLoginPassword, "view-loginPassword");
        stageComponent.getStage().setTitle("Lakes to see");
        stageComponent.changeScene();
    }


}