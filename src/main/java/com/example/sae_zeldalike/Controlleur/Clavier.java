package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.modele.Item.*;
import com.example.sae_zeldalike.modele.Item.NonStockable.*;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arc;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Epée;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Stockable;
import com.example.sae_zeldalike.modele.Item.StockableDansPortefeuille.Piece;
import com.example.sae_zeldalike.modele.Personnage.*;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Projectile.Fleche;
import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashSet;

public class Clavier implements EventHandler<KeyEvent> {

    private Link link;
    private Pane pane;
    private HashSet<KeyCode> touches;
    private Environnement environnement;
    private Timeline mouvementContinu;

    public Clavier(Link link, Pane pane, Environnement environnement) {
        this.link = link;
        this.pane = pane;
        this.touches = new HashSet<>();
        this.environnement = environnement;
        initTimeline();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            touches.add(keyEvent.getCode());
        }if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            touches.remove(keyEvent.getCode());
            if (touches.isEmpty()) {
                link.setDirection("Inactif_" + link.getDirection());
            }
        }
//        interactionTouche();
    }

    private void initTimeline() {
        mouvementContinu = new Timeline(new KeyFrame(Duration.seconds(0.041), ev -> interactionTouche()));
        mouvementContinu.setCycleCount(Timeline.INDEFINITE);
        mouvementContinu.play();
    }

    public void interactionTouche() {


        if (link.estVivant()) {

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
                boolean laisserSurTerrain = false;
                if (item != null) {
                    if (item instanceof Piece) {
                        link.ajouterPiece(((Piece) item).getValeur());

                    }
                    if (item instanceof Stockable) {

                        if (link.emplacementInventaireLibre()) {
                            link.ajouteItemDansInventaire((Stockable) item);

                        } else {
                            System.out.println("Inventaire plein");
                            laisserSurTerrain = true;
                        }

                    }
                    if (item instanceof CoeurRouge) {
                        if (link.getPointDeVieMax() >= link.getPointVie() + ((CoeurRouge) item).getPointDeVie()) {
                            link.setPointVie(link.getPointVie() + ((CoeurRouge) item).getPointDeVie());

                        } else {
                            laisserSurTerrain = true;
                        }
                    }
                    if (item instanceof CoeurBleu) {
                        link.ajouterBouclier(((CoeurBleu) item).getVieAdditionelle());

                    }
                    if (item instanceof SuperMegaFast) {
                        link.setVitesseDeplacementProperty(link.getVitesseDeplacement() + ((SuperMegaFast) item).getVitesse());
                    }
                    if (item instanceof Poison) {
                        link.ajouterEffet((Effet) item);
                    }
                    if (!laisserSurTerrain) {
                        link.getEnvironnement().supprimerItem(item);
                    }
                }
            }
            if (touches.contains(KeyCode.A)) {
                System.out.println(link.getEffets());
                for (Item item : link.getEnvironnement().getItems()) {
                    if (item instanceof Arc) {
                        System.out.println(item);
                    }
                }

                System.out.println(link.getArmeEquiper());
            }
            if (touches.contains(KeyCode.I)) {
                link.utiliserItemDansInventaire();

//                for (Personnage p : link.getEnvironnement().getPersonnages()) {
//                    if (link.getEffets().size() != 0) {
//                        for (int i=0;i<link.getEffets().size();i++) {
//                            link.getEffets().get(i).appliquer(p);
//                        }
//
//                    }
//                }

            }
            if (touches.contains(KeyCode.O)) {
                link.setArmeEquiper(null);
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
            if (touches.contains(KeyCode.QUOTE)) {
                link.getInventaire().setCaseActuel(3);
            }
            if (touches.contains(KeyCode.DIGIT5)) {
                link.getInventaire().setCaseActuel(4);
            }
            if (touches.contains(KeyCode.DIGIT6)) {
                link.getInventaire().setCaseActuel(5);
            }
        }

        if (touches.contains(KeyCode.UP)) {
            if (this.link.getArmeEquiper() != null) {
                link.getArmeEquiper().setDirection("UP");
                link.getArmeEquiper().utiliserCapacite();
            }
        }
        if (touches.contains(KeyCode.DOWN)) {
            if (this.link.getArmeEquiper() != null) {
                link.getArmeEquiper().setDirection("DOWN");
                link.getArmeEquiper().utiliserCapacite();
            }
        }
        if (touches.contains(KeyCode.RIGHT)) {
            if (this.link.getArmeEquiper() != null) {
                link.getArmeEquiper().setDirection("RIGHT");
                link.getArmeEquiper().utiliserCapacite();
            }
        }
        if (touches.contains(KeyCode.LEFT)) {
            if (this.link.getArmeEquiper() != null) {
                link.getArmeEquiper().setDirection("LEFT");
                link.getArmeEquiper().utiliserCapacite();
            }
        }

    }


}





