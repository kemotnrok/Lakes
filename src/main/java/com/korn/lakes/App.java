package com.korn.lakes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.security.SecureRandom;
import java.util.Base64;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

//        Font laden
        try {
            Font.loadFont(Objects.requireNonNull(getClass().getResource("/com/korn/lakes/font/Rubik-Regular.ttf")).toExternalForm(), 10);
            Font.loadFont(Objects.requireNonNull(getClass().getResource("/com/korn/lakes/font/Rubik-Bold.ttf")).toExternalForm(), 10);
        } catch (Exception e) {
            System.out.println("Keine Schrift geladen, " + e.getMessage());
        }

//        Szene starten
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view-fxml/view-loginEmail.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 440, 340);
        stage.setTitle("Lakes to see");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}