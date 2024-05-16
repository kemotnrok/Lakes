package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class ViewController_loginPassword {

    @FXML
    private Button zurueckToLoginEmail;

    @FXML
    private Button weiterToLandingPage;

    @FXML
    private Node eyeLoginPassword;

    @FXML
    protected void onZurueckToLoginEmail() {
        StageComponent stageComponent = new StageComponent(zurueckToLoginEmail, "view-loginEmail");
        stageComponent.getStage().setTitle("Lakes to see");
        stageComponent.changeScene();
    }

    @FXML
    protected void onWeiterToLandingPage() {
        StageComponent stageComponent = new StageComponent(weiterToLandingPage, "view-landingPage");
        stageComponent.getStage().setTitle("Lakes to see");
        stageComponent.changeScene();
    }

    @FXML
    protected void showPasswordLogin(){
        ViewHelper.unhidePassword(eyeLoginPassword);
    }



}