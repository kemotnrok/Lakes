package com.korn.lakes.view;

import com.korn.lakes.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class V_Helper_changeScene {
    private final Stage stage;
    private final Object controller;

    /**
     * Constructor javaFxElement
     * @param javafxElement Node
     * @param view String
     */
    public V_Helper_changeScene(Node javafxElement, String view) {
        try {
            stage = (Stage) javafxElement.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view-fxml/" + view + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            controller = fxmlLoader.getController();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getController() {
        return (T) controller;
    }

    public Stage getStage() {
        return stage;
    }
}
