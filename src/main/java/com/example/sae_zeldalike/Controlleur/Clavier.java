package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.modele.Personnage.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Clavier implements EventHandler<KeyEvent> {

    private Personnage personnage;
    private StringProperty direction;


    public Clavier(Personnage p) {
        this.personnage = p;
        this.direction=new SimpleStringProperty("DOWN");
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        interactionTouche(keyEvent);
    }


    private void interactionTouche(KeyEvent keyEvent) {

        switch (keyEvent.getCode()) {
            case Z -> {
                if (personnage.getEnvironnement().estDansLimiteTerrain(personnage.getPositionX(), personnage.getPositionY()- personnage.getVitesseDeplacement())==true) {
                    personnage.setPositionYProperty(personnage.getPositionY() - personnage.getVitesseDeplacement());
                }
                setDirection("UP");

            }
            case S -> {
                if (personnage.getEnvironnement().estDansLimiteTerrain(personnage.getPositionX(), personnage.getPositionY()+ personnage.getVitesseDeplacement())==true) {
                    personnage.setPositionYProperty(personnage.getPositionY() + personnage.getVitesseDeplacement());

                }
                setDirection("DOWN");
            }
            case Q -> {
                if (personnage.getEnvironnement().estDansLimiteTerrain((personnage.getPositionX()- personnage.getVitesseDeplacement()), personnage.getPositionY())==true) {
                    personnage.setPositionXProperty(personnage.getPositionX() - personnage.getVitesseDeplacement());

                }
                setDirection("LEFT");
            }
            case D -> {
                if (personnage.getEnvironnement().estDansLimiteTerrain((personnage.getPositionX()+ personnage.getVitesseDeplacement()), personnage.getPositionY())==true) {
                    personnage.setPositionXProperty(personnage.getPositionX() + personnage.getVitesseDeplacement());
                }
                setDirection("RIGHT");

            }
        }
        System.out.println("Position X : " + personnage.getPositionX() + " Position Y : " + personnage.getPositionY());

    }

    public String getDirection() {
        return direction.getValue();
    }
    public StringProperty getDirectionProperty() { return direction;}

    public void setDirection(String direction) {
        this.direction.setValue(direction);
    }
}
