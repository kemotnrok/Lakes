package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class ViewController_createAccountPassword {

    @FXML
    private Button weiterToConfirmAccountFromPassword;

    @FXML
    private Button zurueckToAccountEmailFromPassword;

    @FXML
    private Node eyeLoginPassword;

    @FXML
    protected void onWeiterToConfirmAccount(){
        StageComponent stageComponent = new StageComponent(weiterToConfirmAccountFromPassword, "view-confirmAccount");
        stageComponent.getStage().setTitle("Lakes to see");
        stageComponent.changeScene();
    }

    @FXML
    protected void onZurueckToLoginEmail() {
        StageComponent stageComponent = new StageComponent(zurueckToAccountEmailFromPassword, "view-loginEmail");
        stageComponent.getStage().setTitle("Lakes to see");
        stageComponent.changeScene();
    }

    @FXML
    protected void showPasswordLogin(){
        ViewHelper.unhidePassword(eyeLoginPassword);
    }
}
