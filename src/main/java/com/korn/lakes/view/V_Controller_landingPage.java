package com.korn.lakes.view;

import com.korn.lakes.model.DTO.Lake;
import com.korn.lakes.model.M_DbService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class V_Controller_landingPage {

    @FXML
    private TableView<Lake> tableLakes;
    @FXML
    private TableColumn<Lake, String> columnSee, columnStaat, columnFlaeche, columnSeehoehe, columnTemperatur, columnBewertung, columnNotiz;

    @FXML
    public void initialize() {
        columnSee.setCellValueFactory(new PropertyValueFactory<>("see"));
        columnStaat.setCellValueFactory(new PropertyValueFactory<>("staat"));
        columnFlaeche.setCellValueFactory(new PropertyValueFactory<>("flaeche"));
        columnSeehoehe.setCellValueFactory(new PropertyValueFactory<>("seehoehe"));
        columnTemperatur.setCellValueFactory(new PropertyValueFactory<>("temperatur"));
        columnBewertung.setCellValueFactory(new PropertyValueFactory<>("bewertung"));
        columnNotiz.setCellValueFactory(new PropertyValueFactory<>("notiz"));

        tableLakes.setItems(M_DbService.findLakes());
    }

}
