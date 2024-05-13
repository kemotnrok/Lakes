package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class ViewControllerLogin {

    @FXML
    private TextField email;

    @FXML
    private Hyperlink createAccount;

    @FXML
    private Button weiter;

    @FXML
    protected void onCreateAccount() {
        StageComponent stageComponent = new StageComponent(createAccount, "view-createAccountEmail");
        stageComponent.getStage().setTitle("Konto erstellen");
        stageComponent.changeScene();
    }
}