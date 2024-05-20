package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.modele.Personnage.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Clavier implements EventHandler<KeyEvent> {

    private Personnage personnage;
    private String direction;


    public Clavier(Personnage p) {
        this.personnage = p;
        this.direction=null;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        interactionTouche(keyEvent);
    }


    private void interactionTouche(KeyEvent keyEvent) {

        switch (keyEvent.getCode()) {
            case Z -> {
                personnage.setPositionYProperty(personnage.getPositionY() - personnage.getVitesseDeplacement());
                setDirection(new String("UP"));
            }
            case S -> {
                personnage.setPositionYProperty(personnage.getPositionY() + personnage.getVitesseDeplacement());
                setDirection(new String("DOWN"));
            }
            case Q -> {
                personnage.setPositionXProperty(personnage.getPositionX() - personnage.getVitesseDeplacement());
                setDirection(new String("LEFT"));
            }
            case D -> {
                personnage.setPositionXProperty(personnage.getPositionX() + personnage.getVitesseDeplacement());
                setDirection(new String("RIGHT"));
            }
        }
        System.out.println("Position X : " + personnage.getPositionX() + " Position Y : " + personnage.getPositionY());

    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
