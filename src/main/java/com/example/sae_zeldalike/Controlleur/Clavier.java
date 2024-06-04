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

    private Link link;
    private Pane pane;
    private HashSet<KeyCode> touches;
    private Environnement environnement;
    private Timeline mouvementContinu;
    private Timeline tirTimeline;

    public Clavier(Link link, Pane pane, Environnement environnement) {
        this.link = link;
        this.pane = pane;
        this.touches = new HashSet<>();
        this.environnement = environnement;
        initTimeline();
        this.tirTimeline = new Timeline();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            touches.add(keyEvent.getCode());
        } else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            touches.remove(keyEvent.getCode());
            if (touches.isEmpty()) {
                link.setDirection("Inactif_" + link.getDirection());
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
            newX = link.getPositionX();
            newY = link.getPositionY() - link.getVitesseDeplacement();
            if (!link.getEnvironnement().estDevantObstacle(link.hitbox(newX, newY))) {
                link.setPositionYProperty(newY);
            }
            link.setDirection("UP");
        }
        if (touches.contains(KeyCode.S)) {
            newX = link.getPositionX();
            newY = link.getPositionY() + link.getVitesseDeplacement();
            if (!link.getEnvironnement().estDevantObstacle(link.hitbox(newX, newY))) {
                link.setPositionYProperty(newY);
            }
            link.setDirection("DOWN");
        }
        if (touches.contains(KeyCode.Q)) {
            newX = link.getPositionX() - link.getVitesseDeplacement();
            newY = link.getPositionY();
            if (!link.getEnvironnement().estDevantObstacle(link.hitbox(newX, newY))) {
                link.setPositionXProperty(newX);
            }
            link.setDirection("LEFT");
        }
        if (touches.contains(KeyCode.D)) {
            newX = link.getPositionX() + link.getVitesseDeplacement();
            newY = link.getPositionY();
            if (!link.getEnvironnement().estDevantObstacle(link.hitbox(newX, newY))) {
                link.setPositionXProperty(newX);
            }
            link.setDirection("RIGHT");
        }
        if (touches.contains(KeyCode.J)) {
            Item item = link.essaiRamasserItem();
            if (item != null) {
                if (item instanceof Piece) {
                    link.ajouterPiece(((Piece) item).getValeur());
                    link.getEnvironnement().supprimerItem(item);
                }if (item instanceof Stockable){

                    if (link.emplacementInventaireLibre()){

                        link.ajouteItemDansInventaire((Stockable) item);
                        link.getEnvironnement().supprimerItem(item);
                    }else {
                        System.out.println("Inventaire plein");
                    }

                }
            }
        }
        if (touches.contains(KeyCode.A)) {
            System.out.println(link.getInventaire());
            System.out.println(link.getEnvironnement().getPersonnages());
            System.out.println(link.getEnvironnement().getFlèchesEnDéplacement());
        }
        if (touches.contains(KeyCode.I)){

            link.utiliserItemDansInventaire();
        }
        if (touches.contains(KeyCode.U)){

        }
        if (touches.contains(KeyCode.Y)){

        }
        if (touches.contains(KeyCode.AMPERSAND)){
            System.out.println("Case de l'inventaire 1");
        }
        if (touches.contains(KeyCode.E)){
            System.out.println("Case de l'inventaire 2");
        }
        if (touches.contains(KeyCode.QUOTEDBL)){
            System.out.println("Case de l'inventaire 3");
        }

        if(touches.contains(KeyCode.UP)
                && !touches.contains(KeyCode.LEFT)
                && !touches.contains(KeyCode.DOWN)
                && !touches.contains(KeyCode.RIGHT)){
            Flèche flèche = new Flèche(link.getPositionX()+16, link.getPositionY()+16, this.environnement, link.getArc());
            flèche.setDirection("UP");
            link.getArc().getFleches().add(flèche);
            link.tirerFleche();
        }
        if(touches.contains(KeyCode.DOWN)
                && !touches.contains(KeyCode.UP)
                && !touches.contains(KeyCode.LEFT)
                && !touches.contains(KeyCode.RIGHT)){
            Flèche flèche = new Flèche(link.getPositionX()+16, link.getPositionY()+16, this.environnement, link.getArc());
            flèche.setDirection("DOWN");
            link.getArc().getFleches().add(flèche);
            link.tirerFleche();

        }
        if(touches.contains(KeyCode.RIGHT)
                && !touches.contains(KeyCode.UP)
                && !touches.contains(KeyCode.DOWN)
                && !touches.contains(KeyCode.LEFT)){
            Flèche flèche = new Flèche(link.getPositionX()+16, link.getPositionY()+16, this.environnement, link.getArc());
            flèche.setDirection("RIGHT");
            link.getArc().getFleches().add(flèche);
            link.tirerFleche();

        }
        if(touches.contains(KeyCode.LEFT)
                && !touches.contains(KeyCode.UP)
                && !touches.contains(KeyCode.DOWN)
                && !touches.contains(KeyCode.RIGHT)){
            Flèche flèche = new Flèche(link.getPositionX()+16, link.getPositionY()+16, this.environnement, link.getArc());
            flèche.setDirection("LEFT");
            link.getArc().getFleches().add(flèche);
            link.tirerFleche();

    }
    }
}
