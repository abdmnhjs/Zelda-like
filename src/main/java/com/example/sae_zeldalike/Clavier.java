package com.example.sae_zeldalike;

import com.example.sae_zeldalike.modele.Personnage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Clavier implements EventHandler<KeyEvent> {

    private Personnage personnage;

    public Clavier(Personnage p){
        this.personnage=p;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.Z){
            personnage.setPositionYProperty(personnage.getPositionY() - personnage.getVitesseDeplacement());
        }
        if(keyEvent.getCode() == KeyCode.S){
            personnage.setPositionYProperty(personnage.getPositionY() + personnage.getVitesseDeplacement());
        }
        if(keyEvent.getCode() == KeyCode.Q){
            personnage.setPositionXProperty(personnage.getPositionX() - personnage.getVitesseDeplacement());
        }
        if(keyEvent.getCode() == KeyCode.D){
            personnage.setPositionXProperty(personnage.getPositionX() + personnage.getVitesseDeplacement());
        }

        /*switch (keyEvent.getCode()){
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
        }*/
    }
}
