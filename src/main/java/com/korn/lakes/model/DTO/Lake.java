package com.korn.lakes.model.DTO;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Lake {
    private final SimpleStringProperty see;
    private final SimpleStringProperty staat;
    private final SimpleStringProperty notiz;
    private final SimpleDoubleProperty bewertung;

//    ----------

    public Lake(String see, String staat, String notiz, double bewertung) {
        this.see = new SimpleStringProperty(see);
        this.staat = new SimpleStringProperty(staat);
        this.notiz = new SimpleStringProperty(notiz);
        this.bewertung = new SimpleDoubleProperty(bewertung) ;
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

    public SimpleDoubleProperty bewertungProperty() {
        return bewertung;
    }

    public void setBewertung(double bewertung) {
        this.bewertung.set(bewertung);
    }
}


