package com.korn.lakes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        ((ViewController_loginEmail) stageComponent.getController()).setEmailField(createAccountEmail.getText());
        stageComponent.changeScene();
    }

    @FXML
    protected void onWeiterToCreateAccountPassword() {
        StageComponent stageComponent = new StageComponent(weiterToCreateAccountPassword, "view-createAccountPassword");
        Stage nextStage = stageComponent.getStage();
        ViewController_createAccountPassword.setEmailInCreateAccountPassword(createAccountEmail.getText());
        nextStage.setTitle("Lakes to see");
        stageComponent.changeScene();
    }

    /**
     * Transfer email to the previous dialogue box
     * @param emailText String
     */
    protected void setEmail(String emailText){
        createAccountEmail.setText(emailText);
    }

}
