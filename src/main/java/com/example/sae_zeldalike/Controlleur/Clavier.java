package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.modele.Item.*;
import com.example.sae_zeldalike.modele.Personnage.*;

import com.example.sae_zeldalike.Vue.VueFlèche;
import com.example.sae_zeldalike.modele.Environnement.Environnement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import com.example.sae_zeldalike.modele.Item.Arme;

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
        } else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            touches.remove(keyEvent.getCode());
            if (touches.isEmpty()) {
                link.setDirection("Inactif_" + link.getDirection());
            }
        }
    }

    private void initTimeline() {
        mouvementContinu = new Timeline(new KeyFrame(Duration.seconds(0.040), ev -> interactionTouche()));
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
            System.out.println(link.getNumeroCaseActuel());
            System.out.println(link.connaitreIndiceCaseVide());
//            System.out.println(link.longueurTableau());
        }
        if (touches.contains(KeyCode.I)){
            link.utiliserItemDansInventaire();
        }
        if (touches.contains(KeyCode.U)){
            link.setNumeroCaseActuel(link.getNumeroCaseActuel()+1);
        }
        if (touches.contains(KeyCode.Y)){
            link.setNumeroCaseActuel(link.getNumeroCaseActuel()-1);
        }
        if (touches.contains(KeyCode.AMPERSAND)){
            link.setNumeroCaseActuel(0);

//            System.out.println("Case de l'inventaire 0");
        }
        if (touches.contains(KeyCode.UNDEFINED)){
            link.setNumeroCaseActuel(1);

//            System.out.println("Case de l'inventaire 1");
        }
        if (touches.contains(KeyCode.QUOTEDBL)){
            link.setNumeroCaseActuel(2);

//            System.out.println("Case de l'inventaire 2");
        }


        // Gestion des flèches
        if (touches.contains(KeyCode.UP)) {
            lancerFleche(KeyCode.UP);
        }
        if (touches.contains(KeyCode.DOWN)) {
            lancerFleche(KeyCode.DOWN);
        }
        if (touches.contains(KeyCode.RIGHT)) {
            lancerFleche(KeyCode.RIGHT);
        }
        if (touches.contains(KeyCode.LEFT)) {
            lancerFleche(KeyCode.LEFT);
        }


    }

    private void lancerFleche(KeyCode code) {
        for (Arme arme : link.getArmes()) {
            if (arme instanceof Arc) {
                if (!((Arc) arme).getFlèches().isEmpty()) {
                    Flèche flèche = ((Arc) arme).getFlèches().remove(0);
                    VueFlèche vueFlèche;
                    switch (code) {
                        case UP:
                            ((Arc) arme).getFlèches().add(new Flèche(link.getPositionX() + (flèche.getLargeur() / 2), link.getPositionY(), 30, environnement));
                            vueFlèche = new VueFlèche(flèche, pane, "file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-haut.png");
                            break;
                        case DOWN:
                            ((Arc) arme).getFlèches().add(new Flèche(link.getPositionX() + (flèche.getLargeur() / 2), link.getPositionY() + flèche.getLongueur(), 30, environnement));
                            vueFlèche = new VueFlèche(flèche, pane, "file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-bas.png");
                            break;
                        case RIGHT:
                            ((Arc) arme).getFlèches().add(new Flèche(link.getPositionX() + flèche.getLargeur(), link.getPositionY() + (flèche.getLongueur() / 2), 30, environnement));
                            vueFlèche = new VueFlèche(flèche, pane, "file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-droite.png");
                            break;
                        case LEFT:
                            ((Arc) arme).getFlèches().add(new Flèche(link.getPositionX(), link.getPositionY() + (flèche.getLongueur() / 2), 30, environnement));
                            vueFlèche = new VueFlèche(flèche, pane, "file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-gauche.png");
                            break;
                        default:
                            return;
                    }
                    ((Arc) arme).getFlèchesEnDéplacement().add(vueFlèche);
                    vueFlèche.creerFlèche(pane);
                    if (flèche.estDevantObstacle(flèche.getX(), flèche.getY()) || flèche.estDansLimiteTerrain(flèche.getX(), flèche.getY())) {
                        vueFlèche.supprimerFlèche(pane);
                        ((Arc) arme).getFlèchesEnDéplacement().remove(vueFlèche);
                    }
                }
            }
        }
    }



}
