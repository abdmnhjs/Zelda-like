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

import java.util.ArrayList;
import java.util.HashSet;

public class Clavier implements EventHandler<KeyEvent> {

    private Personnage personnage;
    private HashSet<KeyCode> touches;

    public Clavier(Personnage p) {
        this.personnage = p;
        this.touches = new HashSet<>();
    }

    @Override
    public void handle(KeyEvent keyEvent)
    {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            if (!touches.contains(keyEvent.getCode())) {
                touches.add(keyEvent.getCode());
            }
        } if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            touches.remove(keyEvent.getCode());
        }
        interactionTouche();
        if (touches.contains(null)){
            personnage.setDirection("Inactif_"+personnage.getDirection());
        }
    }

    private void interactionTouche() {

        int newX;
        int newY;
        if (touches.contains(KeyCode.Z)) {
            newX = personnage.getPositionX();
            newY = (personnage.getPositionY() - personnage.getVitesseDeplacement());
            if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                personnage.setPositionYProperty(newY);
            }
            personnage.setDirection("UP");
        }
        if (touches.contains(KeyCode.S)) {

            newX = personnage.getPositionX();
            newY = personnage.getPositionY() + personnage.getVitesseDeplacement();
            if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                personnage.setPositionYProperty(personnage.getPositionY() + personnage.getVitesseDeplacement());
            }
            personnage.setDirection("DOWN");
        }
        if (touches.contains(KeyCode.Q)) {
            newX = personnage.getPositionX() - personnage.getVitesseDeplacement();
            newY = personnage.getPositionY();
            if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                personnage.setPositionXProperty(personnage.getPositionX() - personnage.getVitesseDeplacement());
            }
            personnage.setDirection("LEFT");
        }if (touches.contains(KeyCode.D)) {
            newX = personnage.getPositionX() + personnage.getVitesseDeplacement();
            newY = personnage.getPositionY();
            if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                personnage.setPositionXProperty(personnage.getPositionX() + personnage.getVitesseDeplacement());
            }
            personnage.setDirection("RIGHT");
        }
                personnage.setPointVieProperty(1);
                System.out.println("Position X : " + personnage.getPositionX() + " Position Y : " + personnage.getPositionY());

    }

}

