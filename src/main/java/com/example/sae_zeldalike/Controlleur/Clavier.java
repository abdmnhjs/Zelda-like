package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.Vue.VueEpee;
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
    private VueEpee vueEpee;


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
        interactionToucheCombat(keyEvent);
    }

    public void initTimeline() {
        mouvementContinu = new Timeline(new KeyFrame(Duration.seconds(0.017), ev -> {
            interactionTouche();
        }));
        mouvementContinu.setCycleCount(Timeline.INDEFINITE);
        mouvementContinu.play();
    }

    public void interactionTouche() {

        if (link.estVivant()) {

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
                item = link.essaiRamasserItem();
                if (item != null) {
                    if (item instanceof Piece) {
                        link.ajouterPiece(((Piece) item).getValeur());
                        link.getEnvironnement().supprimerItem(item);
                    }
                    if (item instanceof Stockable) {

                    if (this.link.emplacementInventaireLibre()){
                        if (link.emplacementInventaireLibre()) {

                        this.link.ajouteItemDansInventaire((Stockable) item);
                        this.link.getEnvironnement().supprimerItem(item);
                    }else {
                        System.out.println("Inventaire plein");
                    }
                            link.ajouteItemDansInventaire((Stockable) item);
                            link.getEnvironnement().supprimerItem(item);
                        } else {
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
        }
                    }
                    if (item instanceof CoeurRouge) {
                        if (link.getPointDeVieMax() >= link.getPointVie() + ((CoeurRouge) item).getPointDeVie()) {
                            link.setPointVie(link.getPointVie() + ((CoeurRouge) item).getPointDeVie());
                            link.getEnvironnement().supprimerItem(item);
                        }
                    }
                    if (item instanceof CoeurBleu) {
                        link.ajouterBouclier(((CoeurBleu) item).getVieAdditionelle());
                        link.getEnvironnement().supprimerItem(item);
                    }
                }
            }
            if (touches.contains(KeyCode.A)) {
//            System.out.println(link.getInventaire().getCaseActuel());
                System.out.println(link.getInventaire().connaitreIndiceCaseVide());
                System.out.println(link.getInventaire().getInventaire());
                link.reduirePointsDeVie(5);
                System.out.println("Coeur RED " + link.getPointVie());
                System.out.println(link.getPointDeVieAdditionelle());
            }
            if (touches.contains(KeyCode.I)) {
                link.utiliserItemDansInventaire();
            }
            if (touches.contains(KeyCode.U)) {
                link.getInventaire().setCaseActuel(link.getInventaire().getCaseActuel() + 1);
            }
            if (touches.contains(KeyCode.Y)) {
                link.getInventaire().setCaseActuel(link.getInventaire().getCaseActuel() - 1);
            }
            if (touches.contains(KeyCode.AMPERSAND)) {
                link.getInventaire().setCaseActuel(0);

//            System.out.println("Case de l'inventaire 0");
            }
            if (touches.contains(KeyCode.UNDEFINED)) {
                link.getInventaire().setCaseActuel(1);

//            System.out.println("Case de l'inventaire 1");
            }
            if (touches.contains(KeyCode.QUOTEDBL)) {
                link.getInventaire().setCaseActuel(2);

//            System.out.println("Case de l'inventaire 2");
            }


    }public void interactionToucheCombat(KeyEvent keyEvent){
        if(this.link.arcEquipe()){
            switch (keyEvent.getCode()){
                case UP -> {
                    Flèche flèche = new Flèche(this.link.getPositionX()+16, this.link.getPositionY()+16, this.environnement, this.link.getArc());
                    flèche.setDirection("UP");
                    this.link.getArc().getFleches().add(flèche);
                    this.link.tirerFleche();
                }
                case DOWN -> {
                    Flèche flèche = new Flèche(this.link.getPositionX()+16, this.link.getPositionY()+16, this.environnement, this.link.getArc());
                    flèche.setDirection("DOWN");
                    this.link.getArc().getFleches().add(flèche);
                    this.link.tirerFleche();
                }
                case RIGHT -> {
                    Flèche flèche = new Flèche(this.link.getPositionX()+16, this.link.getPositionY()+16, this.environnement, this.link.getArc());
                    flèche.setDirection("RIGHT");
                    this.link.getArc().getFleches().add(flèche);
                    this.link.tirerFleche();
                }
                case LEFT -> {
                    Flèche flèche = new Flèche(this.link.getPositionX()+16, this.link.getPositionY()+16, this.environnement, this.link.getArc());
                    flèche.setDirection("LEFT");
                    this.link.getArc().getFleches().add(flèche);
                    this.link.tirerFleche();
                }
            }
        }
        if(this.link.epeeEquipee()){
            Epée epée = new Epée(this.link.getPositionX()+16, this.link.getPositionY()+16, 50, 15, this.environnement);
            switch (keyEvent.getCode()){
                case UP -> {
                    epée.setDirection("UP");
                    this.link.utiliserEpee(epée);
                }
                case DOWN -> {
                    epée.setDirection("DOWN");
                    this.link.utiliserEpee(epée);
                }
                case RIGHT -> {
                    epée.setDirection("RIGHT");
                    this.link.utiliserEpee(epée);
                }
                case LEFT -> {
                    epée.setDirection("LEFT");
                    this.link.utiliserEpee(epée);
                }
            }
        }

    }
}

