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
    private String directionAttaque;
    private Environnement environnement;
    private Timeline mouvementContinu;


    public Clavier(Link link, Pane pane, Environnement environnement) {
        this.link = link;
        this.pane = pane;
        this.touches = new HashSet<>();
        this.environnement = environnement;
        this.directionAttaque = "";
        initTimeline();
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
        System.out.println(touches);
    }

    public void initTimeline() {
        mouvementContinu = new Timeline(new KeyFrame(Duration.seconds(0.017), ev -> interactionTouche()));
        mouvementContinu.setCycleCount(Timeline.INDEFINITE);
        mouvementContinu.play();
    }

    public void interactionTouche() {
        int newX;
        int newY;

        if (this.touches.contains(KeyCode.Z)) {
            newX = this.link.getPositionX();
            newY = this.link.getPositionY() - this.link.getVitesseDeplacement();
            if (!this.link.getEnvironnement().estDevantObstacle(this.link.hitbox(newX, newY))) {
                this.link.setPositionYProperty(newY);
            }
            this.link.setDirection("UP");
        }
        if (this.touches.contains(KeyCode.S)) {
            newX = this.link.getPositionX();
            newY = this.link.getPositionY() + this.link.getVitesseDeplacement();
            if (!this.link.getEnvironnement().estDevantObstacle(this.link.hitbox(newX, newY))) {
                this.link.setPositionYProperty(newY);
            }
            this.link.setDirection("DOWN");
        }
        if (this.touches.contains(KeyCode.Q)) {
            newX = this.link.getPositionX() - this.link.getVitesseDeplacement();
            newY = this.link.getPositionY();
            if (!this.link.getEnvironnement().estDevantObstacle(this.link.hitbox(newX, newY))) {
                this.link.setPositionXProperty(newX);
            }
            link.setDirection("LEFT");
        }
        if (this.touches.contains(KeyCode.D)) {
            newX = this.link.getPositionX() + this.link.getVitesseDeplacement();
            newY = this.link.getPositionY();
            if (!this.link.getEnvironnement().estDevantObstacle(this.link.hitbox(newX, newY))) {
                this.link.setPositionXProperty(newX);
            }
            this.link.setDirection("RIGHT");
        }
        if (this.touches.contains(KeyCode.J)) {
            Item item = this.link.essaiRamasserItem();
            if (item != null) {
                if (item instanceof Piece) {
                    this.link.ajouterPiece(((Piece) item).getValeur());
                    this.link.getEnvironnement().supprimerItem(item);
                }if (item instanceof Stockable){

                    if (this.link.emplacementInventaireLibre()){

                        this.link.ajouteItemDansInventaire((Stockable) item);
                        this.link.getEnvironnement().supprimerItem(item);
                    }else {
                        System.out.println("Inventaire plein");
                    }

                }
            }
        }
        if (this.touches.contains(KeyCode.A)) {
            System.out.println(this.link.getInventaire());
            System.out.println(this.link.getEnvironnement().getPersonnages());
            System.out.println(this.link.getEnvironnement().getFlèchesEnDéplacement());
        }
        if (this.touches.contains(KeyCode.I)){

            this.link.utiliserItemDansInventaire();
        }
        if (this.touches.contains(KeyCode.U)){

        }
        if (this.touches.contains(KeyCode.Y)){

        }
        if (this.touches.contains(KeyCode.AMPERSAND)){
            System.out.println("Case de l'inventaire 1");
        }
        if (this.touches.contains(KeyCode.E)){
            System.out.println("Case de l'inventaire 2");
        }
        if (this.touches.contains(KeyCode.QUOTEDBL)){
            System.out.println("Case de l'inventaire 3");
        }


            if(this.touches.contains(KeyCode.UP)){
                if(!this.touches.contains(KeyCode.LEFT)
                        && !this.touches.contains(KeyCode.DOWN)
                        && !this.touches.contains(KeyCode.RIGHT)){
                    //System.out.println("attaqque arc direction up");
                    Flèche flèche = new Flèche(this.link.getPositionX()+16, this.link.getPositionY()+16, this.environnement, this.link.getArc());
                    flèche.setDirection("UP");
                    this.link.getArc().getFleches().add(flèche);
                    this.link.tirerFleche();
                }

            }
            if(this.touches.contains(KeyCode.DOWN)){
                if(!this.touches.contains(KeyCode.UP)
                        && !this.touches.contains(KeyCode.LEFT)
                        && !this.touches.contains(KeyCode.RIGHT)){
                    //System.out.println("attaqque arc direction bas");
                    Flèche flèche = new Flèche(this.link.getPositionX()+16, this.link.getPositionY()+16, this.environnement, this.link.getArc());
                    flèche.setDirection("DOWN");
                    this.link.getArc().getFleches().add(flèche);
                    this.link.tirerFleche();
                }


            }
            if(this.touches.contains(KeyCode.RIGHT)){
                if(!this.touches.contains(KeyCode.UP)
                        && !this.touches.contains(KeyCode.DOWN)
                        && !this.touches.contains(KeyCode.LEFT)){
                    //System.out.println("attaqque arc direction droite");
                    Flèche flèche = new Flèche(this.link.getPositionX()+16, this.link.getPositionY()+16, this.environnement, this.link.getArc());
                    flèche.setDirection("RIGHT");
                    this.link.getArc().getFleches().add(flèche);
                    this.link.tirerFleche();
                }

            }
            if(this.touches.contains(KeyCode.LEFT)){
                if(!this.touches.contains(KeyCode.UP)
                        && !this.touches.contains(KeyCode.DOWN)
                        && !this.touches.contains(KeyCode.RIGHT)){
                    //System.out.println("attaqque arc direction gauche");
                    Flèche flèche = new Flèche(this.link.getPositionX()+16, this.link.getPositionY()+16, this.environnement, this.link.getArc());
                    flèche.setDirection("LEFT");
                    this.link.getArc().getFleches().add(flèche);
                    this.link.tirerFleche();
                }
            }
        }
    }

