package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ViewController_loginPassword {

    private static String emailInLoginPassword;

    @FXML
    private Button zurueckToLoginEmail;

    @FXML
    private Button weiterToLandingPage;

    @FXML
    private Node eyeLoginPassword;

//    ---------- Methoden ----------

    @FXML
    protected void onZurueckToLoginEmail() {
        StageComponent stageComponent = new StageComponent(zurueckToLoginEmail, "view-loginEmail");
        Stage nextStage = stageComponent.getStage();
        nextStage.setTitle("Lakes to see");
        ((ViewController_loginEmail) stageComponent.getController()).setEmailField(emailInLoginPassword);
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

//    ---------- Setter/Getter ----------

    public static String getEmailInLoginPassword() {
        return emailInLoginPassword;
    }

    public static void setEmailInLoginPassword(String emailInLoginPassword) {
        ViewController_loginPassword.emailInLoginPassword = emailInLoginPassword;
    }
}