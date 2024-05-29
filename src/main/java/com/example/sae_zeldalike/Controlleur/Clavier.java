package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.Vue.VueFlèche;
import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Arc;
import com.example.sae_zeldalike.modele.Item.Arme;
import com.example.sae_zeldalike.modele.Item.Flèche;
import com.example.sae_zeldalike.modele.Personnage.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Clavier implements EventHandler<KeyEvent> {

    private Personnage personnage;
    private Pane pane;
    Environnement environnement;

    public Clavier(Personnage p, Pane pane, Environnement environnement) {
        this.personnage = p;
        this.pane = pane;
        this.environnement = environnement;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        interactionTouche(keyEvent);
    }

    private void interactionTouche(KeyEvent keyEvent) {

        int newX;
        int newY;
        int newXFlèche;
        int newYFlèche;
        switch (keyEvent.getCode()) {
            case Z -> {
                newX = personnage.getPositionX();
                newY = (personnage.getPositionY()- personnage.getVitesseDeplacement());
                if (!personnage.estDevantObstacle(newX, newY)) {
                    if(!personnage.estDansLimiteTerrain(newX, newY)){
                        personnage.setPositionYProperty(newY);
                    }
                }
                personnage.setDirection("UP");
            }
            case S -> {
                newX = personnage.getPositionX();
                newY = personnage.getPositionY()+ personnage.getVitesseDeplacement();
                if (!personnage.estDevantObstacle(newX, newY)) {
                    if(!personnage.estDansLimiteTerrain(newX, newY)){
                        personnage.setPositionYProperty(newY);
                    }
                }
                personnage.setDirection("DOWN");
            }
            case Q -> {
                newX = personnage.getPositionX()- personnage.getVitesseDeplacement();
                newY = personnage.getPositionY();
                if (!personnage.estDevantObstacle(newX, newY)) {
                    if(!personnage.estDansLimiteTerrain(newX, newY)){
                        personnage.setPositionXProperty(newX);
                    }
                }
                personnage.setDirection("LEFT");
            }
            case D -> {
                newX = personnage.getPositionX()+ personnage.getVitesseDeplacement();
                newY = personnage.getPositionY();
                if (!personnage.estDevantObstacle(newX, newY)) {
                    if(!personnage.estDansLimiteTerrain(newX, newY)){
                        personnage.setPositionXProperty(newX);
                    }
                }
                personnage.setDirection("RIGHT");
            }
            case UP -> {
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
            case DOWN -> {
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
            case RIGHT -> {
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
            case LEFT -> {
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
        System.out.println("Position X : " + personnage.getPositionX() + " Position Y : " + personnage.getPositionY());

    }


}
