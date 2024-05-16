package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class ViewControllerLoginPassword {

    @FXML
    private Button zurueckToLoginEmail;

    @FXML
    private Button weiterToLandingPage;

    @FXML
    private Object eyeLoginPassword;


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
        System.out.println("Passwort einblenden"); // Todo
    }



}