package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ViewControllerCreateAccountEmail {

    @FXML
    private Button zurueckToLoginEmailFromCreateAccount;

    @FXML
    private Button weiterToCreateAccountPassword;

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

}
