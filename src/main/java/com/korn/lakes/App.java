package com.korn.lakes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml-views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[32];
//        random.nextBytes(salt);
//        String saltString = Base64.getUrlEncoder().encodeToString(salt);
//        System.out.println(saltString);

        launch();
    }
}