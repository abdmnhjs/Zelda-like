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
                if (personnage.getDirection().equals("UP")) {
                    System.out.println("a lancé flèche");
                    for (Arme arme : personnage.getArmes()) {
                        if (arme instanceof Arc) {
                            System.out.println("avant avant : " + ((Arc) arme).getFlèches());
                            if (!((Arc) arme).getFlèches().isEmpty()) {
                                Flèche flèche = ((Arc) arme).getFlèches().remove(0);
                                System.out.println("avant : " + ((Arc) arme).getFlèches());
                                ((Arc) arme).getFlèches().add(new Flèche(personnage.getPositionX(), personnage.getPositionY(), 5, 5, this.environnement));
                                System.out.println("après : " + ((Arc) arme).getFlèches());
                                flèche.setxProperty(personnage.getPositionX() + 16);
                                VueFlèche vueFlèche = new VueFlèche(flèche, this.pane);
                                ((Arc) arme).getFlèchesEnDéplacement().add(vueFlèche);
                                ((Arc) arme).getFlèchesEnDéplacement().get(0).creerFlèche(this.pane);
                                while (flèche.getY() > 0){
                                    flèche.setyProperty(flèche.getY() - flèche.getVitesseProperty());
                                    if (flèche.estDevantObstacle(flèche.getX(), flèche.getY()) || flèche.estDansLimiteTerrain(flèche.getX(), flèche.getY())) {
                                        vueFlèche.supprimerFlèche(this.pane);
                                        ((Arc) arme).getFlèchesEnDéplacement().remove(vueFlèche);
                                        break;
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        System.out.println("Position X : " + personnage.getPositionX() + " Position Y : " + personnage.getPositionY());

    }


}
