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

    public Clavier(Personnage p) {
        this.personnage = p;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        interactionTouche(keyEvent);
    }

    private void interactionTouche(KeyEvent keyEvent) {

        int newX;
        int newY;
        switch (keyEvent.getCode()) {
            case Z -> {
                newX = personnage.getPositionX();
                newY = (personnage.getPositionY()- personnage.getVitesseDeplacement());
                if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                        personnage.setPositionYProperty(newY);
                    }
                personnage.setDirection("UP");
            }
            case S -> {

                newX = personnage.getPositionX();
                newY = personnage.getPositionY()+ personnage.getVitesseDeplacement();
                if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                    personnage.setPositionYProperty(personnage.getPositionY() + personnage.getVitesseDeplacement());
                }
                personnage.setDirection("DOWN");
            }
            case Q -> {

                newX = personnage.getPositionX()- personnage.getVitesseDeplacement();
                newY = personnage.getPositionY();
                if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                    personnage.setPositionXProperty(personnage.getPositionX() - personnage.getVitesseDeplacement());

                }
                personnage.setDirection("LEFT");
            }
            case D -> {
                newX = personnage.getPositionX()+ personnage.getVitesseDeplacement();
                newY = personnage.getPositionY();
                if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                    personnage.setPositionXProperty(personnage.getPositionX() + personnage.getVitesseDeplacement());
                }
                personnage.setDirection("RIGHT");
            }
        }
        System.out.println("Position X : " + personnage.getPositionX() + " Position Y : " + personnage.getPositionY());

    }


}
