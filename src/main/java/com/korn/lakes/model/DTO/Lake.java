package com.korn.lakes.model.DTO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Lake {
    private final SimpleStringProperty see;
    private final SimpleStringProperty staat;
    private final SimpleDoubleProperty flaeche;
    private final SimpleDoubleProperty seehoehe;
    private final SimpleDoubleProperty temperatur;
    private final SimpleIntegerProperty bewertung;
    private final SimpleStringProperty notiz;

//    ----------

    public Lake(String lakeName) {
        this.see = new SimpleStringProperty(lakeName);
        this.staat = new SimpleStringProperty(null);
        this.flaeche = new SimpleDoubleProperty(0.0);
        this.seehoehe = new SimpleDoubleProperty(0.0);
        this.temperatur = new SimpleDoubleProperty(0.0);
        this.bewertung = new SimpleIntegerProperty(0);
        this.notiz = new SimpleStringProperty(null);

    }

    public Lake(String lakeName, String countryName, double flaeche, double seehoehe, double temperatur, String note, Integer rating) {
        this.see = new SimpleStringProperty(lakeName);
        this.staat = new SimpleStringProperty(countryName);
        this.flaeche = new SimpleDoubleProperty(flaeche);
        this.seehoehe = new SimpleDoubleProperty(seehoehe);
        this.temperatur = new SimpleDoubleProperty(temperatur);
        this.notiz = new SimpleStringProperty(note);
        this.bewertung = new SimpleIntegerProperty(rating) ;
    }

//    ----------

    public String getSee() {
        return see.get();
    }

    public SimpleStringProperty seeProperty() {
        return see;
    }
    public void setSee(String see) {
        this.see.set(see);
    }

    public String getStaat() {
        return staat.get();
    }

    public SimpleStringProperty staatProperty() {
        return staat;
    }

    public void setStaat(String staat) {
        this.staat.set(staat);
    }

    public String getNotiz() {
        return notiz.get();
    }

    public SimpleStringProperty notizProperty() {
        return notiz;
    }

    public void setNotiz(String notiz) {
        this.notiz.set(notiz);
    }

    public double getBewertung() {
        return bewertung.get();
    }

    public SimpleIntegerProperty bewertungProperty() {
        return bewertung;
    }

    public void setBewertung(Integer bewertung) {
        this.bewertung.set(bewertung);
    }

    public double getTemperatur() {
        return temperatur.get();
    }

    public SimpleDoubleProperty temperaturProperty() {
        return temperatur;
    }

    public double getSeehoehe() {
        return seehoehe.get();
    }

    public SimpleDoubleProperty seehoeheProperty() {
        return seehoehe;
    }

    public double getFlaeche() {
        return flaeche.get();
    }

    public SimpleDoubleProperty flaecheProperty() {
        return flaeche;
    }
}


