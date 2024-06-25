package com.korn.lakes.view;

import com.korn.lakes.controller.C_Mail;
import com.korn.lakes.controller.C_SessionData;
import com.korn.lakes.model.DTO.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class V_Controller_verifyWithToken implements Initializable {

    @FXML
    private Button backToLoginEmailFromCreateAccount;
    @FXML
    private Button continueToCreateAccountPassword;
    @FXML
    private TextField createAccountEmailField;
    @FXML
    private Text infoCreateEmail;

    User sessionUser;

//    --------------------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sessionUser = C_SessionData.getSessionUser();

    }

}
