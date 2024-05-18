package com.example.sae_zeldalike;

import com.example.sae_zeldalike.modele.Personnage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class Clavier implements EventHandler<KeyEvent> {

    private Personnage personnage;

    public Clavier(Personnage p){
        this.personnage=p;
    }
    @Override
    public void handle (KeyEvent keyEvent){
        evenementTouche(keyEvent);
    }


    private void evenementTouche(KeyEvent keyEvent) {

        switch (keyEvent.getCode()){
            case Z -> {
                personnage.setPositionYProperty(personnage.getPositionY() - personnage.getVitesseDeplacement());
            }
            case S -> {
                personnage.setPositionYProperty(personnage.getPositionY() + personnage.getVitesseDeplacement());
            }
            case Q -> {
                personnage.setPositionXProperty(personnage.getPositionX() - personnage.getVitesseDeplacement());
            }
            case D -> {
                personnage.setPositionXProperty(personnage.getPositionX() + personnage.getVitesseDeplacement());
            }
        }
        System.out.println("Position X : "+ personnage.getPositionX()+" Position Y : "+ personnage.getPositionY());
    }
}
