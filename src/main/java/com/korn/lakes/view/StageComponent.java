package com.korn.lakes.view;

import com.korn.lakes.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageComponent {
    private Scene scene;
    private Stage stage;
    private FXMLLoader fxmlLoader;
    private Node javafxElement;
    private Object controller;

    /**
     * Constructor javaFxElement
     * @param javafxElement Node
     * @param view String
     */
    public StageComponent(Node javafxElement, String view) {
        try {
            this.javafxElement = javafxElement;
            stage = (Stage) javafxElement.getScene().getWindow();
            fxmlLoader = new FXMLLoader(App.class.getResource("view-fxml/" + view + ".fxml"));
            scene = new Scene(fxmlLoader.load());
            controller = fxmlLoader.getController();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeScene() {
        stage.setScene(scene);
    }

    public <T> T getController() {
        return (T) controller;
    }

    public Stage getStage() {
        return stage;
    }
}
