package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.modele.Item.*;
import com.example.sae_zeldalike.modele.Personnage.*;

import com.example.sae_zeldalike.Vue.VueFlèche;
import com.example.sae_zeldalike.modele.Environnement.Environnement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import com.example.sae_zeldalike.modele.Item.Arme;
import com.example.sae_zeldalike.modele.Personnage.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;


import java.util.ArrayList;
import java.util.HashSet;

public class Clavier implements EventHandler<KeyEvent> {

    private Link personnage;
    private Pane pane;
    private HashSet<KeyCode> touches;
    Environnement environnement;


    public Clavier(Link p, Pane pane, Environnement environnement) {
        this.personnage = p;
        this.pane=pane;
        this.touches = new HashSet<>();
        this.environnement = environnement;
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
        if (touches.isEmpty()){
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
        }if (touches.contains(KeyCode.J)){
            Item item =  personnage.essaiRamasserPiece();
            if (item!=null){
                if (item instanceof Piece){
                    personnage.ajouterPiece(((Piece) item).getValeur());
                    personnage.getEnvironnement().supprimerItem(item);

                }
            }
        }if (touches.contains(KeyCode.A)){
            System.out.println(personnage.getPortefeuille());
        }
//        personnage.setPointVieProperty(1);
//        System.out.println("Position X : " + personnage.getPositionX() + " Position Y : " + personnage.getPositionY());

        if (touches.contains(KeyCode.UP)){
            for (Arme arme : personnage.getArmes()) {
                if (arme instanceof Arc) {
                    if (!((Arc) arme).getFlèches().isEmpty()) {
                        Flèche flèche = ((Arc) arme).getFlèches().remove(0);
                        ((Arc) arme).getFlèches().add(new Flèche(personnage.getPositionX() + (flèche.getLargeur() / 2), personnage.getPositionY(), 30, this.environnement));
                        VueFlèche vueFlèche = new VueFlèche(flèche, this.pane, "file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-haut.png");
                        ((Arc) arme).getFlèchesEnDéplacement().add(vueFlèche);
                        ((Arc) arme).getFlèchesEnDéplacement().get(0).creerFlèche(this.pane);
                        if (flèche.estDevantObstacle(flèche.getX(), flèche.getY()) || flèche.estDansLimiteTerrain(flèche.getX(), flèche.getY())) {
                            vueFlèche.supprimerFlèche(this.pane);
                            ((Arc) arme).getFlèchesEnDéplacement().remove(0);
                        }
                    }
                }
            }
        }
        if (touches.contains(KeyCode.DOWN)){
            for (Arme arme : personnage.getArmes()) {
                if (arme instanceof Arc) {
                    if (!((Arc) arme).getFlèches().isEmpty()) {
                        Flèche flèche = ((Arc) arme).getFlèches().remove(0);
                        ((Arc) arme).getFlèches().add(new Flèche(personnage.getPositionX() + (flèche.getLargeur() / 2), personnage.getPositionY() + flèche.getLongueur(), 30, this.environnement));
                        VueFlèche vueFlèche = new VueFlèche(flèche, this.pane, "file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-bas.png");
                        ((Arc) arme).getFlèchesEnDéplacement().add(vueFlèche);
                        ((Arc) arme).getFlèchesEnDéplacement().get(0).creerFlèche(this.pane);
                        if (flèche.estDevantObstacle(flèche.getX(), flèche.getY()) || flèche.estDansLimiteTerrain(flèche.getX(), flèche.getY())) {
                            vueFlèche.supprimerFlèche(this.pane);
                            ((Arc) arme).getFlèchesEnDéplacement().remove(0);
                        }
                    }
                }
            }
        }
        if (touches.contains(KeyCode.RIGHT)){
            for (Arme arme : personnage.getArmes()) {
                if (arme instanceof Arc) {
                    if (!((Arc) arme).getFlèches().isEmpty()) {
                        Flèche flèche = ((Arc) arme).getFlèches().remove(0);
                        ((Arc) arme).getFlèches().add(new Flèche(personnage.getPositionX() + flèche.getLargeur(), personnage.getPositionY() + (flèche.getLongueur() / 2), 30, this.environnement));
                        VueFlèche vueFlèche = new VueFlèche(flèche, this.pane, "file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-droite.png");
                        ((Arc) arme).getFlèchesEnDéplacement().add(vueFlèche);
                        ((Arc) arme).getFlèchesEnDéplacement().get(0).creerFlèche(this.pane);
                        if (flèche.estDevantObstacle(flèche.getX(), flèche.getY()) || flèche.estDansLimiteTerrain(flèche.getX(), flèche.getY())) {
                            vueFlèche.supprimerFlèche(this.pane);
                            ((Arc) arme).getFlèchesEnDéplacement().remove(0);
                        }
                    }
                }
            }
        }
        if (touches.contains(KeyCode.LEFT)){
            for (Arme arme : personnage.getArmes()) {
                if (arme instanceof Arc) {
                    if (!((Arc) arme).getFlèches().isEmpty()) {
                        Flèche flèche = ((Arc) arme).getFlèches().remove(0);
                        ((Arc) arme).getFlèches().add(new Flèche(personnage.getPositionX(), personnage.getPositionY() + (flèche.getLongueur() / 2), 30, this.environnement));
                        VueFlèche vueFlèche = new VueFlèche(flèche, this.pane, "file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-gauche.png");
                        ((Arc) arme).getFlèchesEnDéplacement().add(vueFlèche);
                        ((Arc) arme).getFlèchesEnDéplacement().get(0).creerFlèche(this.pane);
                        if (flèche.estDevantObstacle(flèche.getX(), flèche.getY()) || flèche.estDansLimiteTerrain(flèche.getX(), flèche.getY())) {
                            vueFlèche.supprimerFlèche(this.pane);
                            ((Arc) arme).getFlèchesEnDéplacement().remove(0);
                        }
                    }
                }
            }
        }
    }

}

