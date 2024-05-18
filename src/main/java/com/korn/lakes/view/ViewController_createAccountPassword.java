package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ViewController_createAccountPassword {

    private static String emailInCreateAccountPassword;

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
        Stage nextStage = stageComponent.getStage();
        ((ViewController_loginEmail) stageComponent.getController()).setEmailField(emailInCreateAccountPassword);
        nextStage.setTitle("Lakes to see");
        stageComponent.changeScene();
    }

    @FXML
    protected void showPasswordLogin(){
        ViewHelper.unhidePassword(eyeLoginPassword);
    }

//    ---------- Setter/Getter ----------

    public static String getEmailInCreateAccountPassword() {
        return emailInCreateAccountPassword;
    }

    public static void setEmailInCreateAccountPassword(String emailInCreateAccountPassword) {
        ViewController_createAccountPassword.emailInCreateAccountPassword = emailInCreateAccountPassword;
    }
}
