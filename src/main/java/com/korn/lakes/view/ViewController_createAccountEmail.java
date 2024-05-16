package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ViewController_createAccountEmail {

    @FXML
    private Button zurueckToLoginEmailFromCreateAccount;

    @FXML
    private Button weiterToCreateAccountPassword;

    @FXML
    private TextField createAccountEmail;

    @FXML
    protected void onZurueckToLoginEmailFromCreateAccount() {
        StageComponent stageComponent = new StageComponent(zurueckToLoginEmailFromCreateAccount, "view-loginEmail");
        stageComponent.getStage().setTitle("Lakes to see");
        stageComponent.changeScene();
    }

    @FXML
    protected void onWeiterToCreateAccountPassword() {
        StageComponent stageComponent = new StageComponent(weiterToCreateAccountPassword, "view-createAccountPassword");
        stageComponent.getStage().setTitle("Lakes to see");
        stageComponent.changeScene();
    }

    protected void setEmail(String emailText){
        createAccountEmail.setText(emailText);
    }

}
