package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.modele.Item.*;
import com.example.sae_zeldalike.modele.Personnage.*;

import com.example.sae_zeldalike.Vue.VueFlèche;
import com.example.sae_zeldalike.modele.Environnement.Environnement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import com.example.sae_zeldalike.modele.Item.Arme;
import com.example.sae_zeldalike.modele.Personnage.*;

import java.util.HashSet;

public class Clavier implements EventHandler<KeyEvent> {

    private Link personnage;
    private Pane pane;
    private HashSet<KeyCode> touches;
    private Environnement environnement;
    private Timeline mouvementContinu;

    public Clavier(Link personnage, Pane pane, Environnement environnement) {
        this.personnage = personnage;
        this.pane = pane;
        this.touches = new HashSet<>();
        this.environnement = environnement;
        initTimeline();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            touches.add(keyEvent.getCode());
        } else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            touches.remove(keyEvent.getCode());
            if (touches.isEmpty()) {
                personnage.setDirection("Inactif_" + personnage.getDirection());
            }
        }
    }

    private void initTimeline() {
        mouvementContinu = new Timeline(new KeyFrame(Duration.seconds(0.017), ev -> interactionTouche()));
        mouvementContinu.setCycleCount(Timeline.INDEFINITE);
        mouvementContinu.play();
    }

    private void interactionTouche() {
        int newX;
        int newY;

        if (touches.contains(KeyCode.Z)) {
            newX = personnage.getPositionX();
            newY = personnage.getPositionY() - personnage.getVitesseDeplacement();
            if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                personnage.setPositionYProperty(newY);
            }
            personnage.setDirection("UP");
        }
        if (touches.contains(KeyCode.S)) {
            newX = personnage.getPositionX();
            newY = personnage.getPositionY() + personnage.getVitesseDeplacement();
            if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                personnage.setPositionYProperty(newY);
            }
            personnage.setDirection("DOWN");
        }
        if (touches.contains(KeyCode.Q)) {
            newX = personnage.getPositionX() - personnage.getVitesseDeplacement();
            newY = personnage.getPositionY();
            if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                personnage.setPositionXProperty(newX);
            }
            personnage.setDirection("LEFT");
        }
        if (touches.contains(KeyCode.D)) {
            newX = personnage.getPositionX() + personnage.getVitesseDeplacement();
            newY = personnage.getPositionY();
            if (!personnage.getEnvironnement().estDevantObstacle(personnage.hitbox(newX, newY))) {
                personnage.setPositionXProperty(newX);
            }
            personnage.setDirection("RIGHT");
        }
        if (touches.contains(KeyCode.J)) {
            Item item = personnage.essaiRamasserPiece();
            if (item != null) {
                if (item instanceof Piece) {
                    personnage.ajouterPiece(((Piece) item).getValeur());
                    personnage.getEnvironnement().supprimerItem(item);
                }
            }
        }
        if (touches.contains(KeyCode.A)) {
            System.out.println(personnage.getPortefeuille());
        }
        if(touches.contains(KeyCode.UP)){
            Flèche flèche = new Flèche(personnage.getPositionX()+16, personnage.getPositionY()+16, 15, this.environnement);
            flèche.setDirection("UP");
            personnage.getEnvironnement().getFlèchesEnDéplacement().add(flèche);
        }
        if(touches.contains(KeyCode.DOWN)){
            Flèche flèche = new Flèche(personnage.getPositionX()+16, personnage.getPositionY()+16, 15, this.environnement);
            flèche.setDirection("DOWN");
            personnage.getEnvironnement().getFlèchesEnDéplacement().add(flèche);

        }
        if(touches.contains(KeyCode.RIGHT)){
            Flèche flèche = new Flèche(personnage.getPositionX()+16, personnage.getPositionY()+16, 15, this.environnement);
            flèche.setDirection("RIGHT");
            personnage.getEnvironnement().getFlèchesEnDéplacement().add(flèche);

        }
        if(touches.contains(KeyCode.LEFT)){
            Flèche flèche = new Flèche(personnage.getPositionX()+16, personnage.getPositionY()+16, 15, this.environnement);
            flèche.setDirection("LEFT");
            personnage.getEnvironnement().getFlèchesEnDéplacement().add(flèche);

        }
    }

}
