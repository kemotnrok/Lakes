package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class ViewControllerLoginEmail {

    @FXML
    private TextField email;

    @FXML
    private Hyperlink createAccount;

    @FXML
    private Button weiterToLoginPassword;

    @FXML
    protected void onCreateAccount() {
        StageComponent stageComponent = new StageComponent(createAccount, "view-createAccountEmail");
        stageComponent.getStage().setTitle("Lakes to see");
        stageComponent.changeScene();
    }

    @FXML
    protected void onWeiterToLoginPassword() {
        StageComponent stageComponent = new StageComponent(weiterToLoginPassword, "view-loginPassword");
        stageComponent.getStage().setTitle("Lakes to see");
        stageComponent.changeScene();
    }

}