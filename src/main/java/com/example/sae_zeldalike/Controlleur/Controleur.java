package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.Vue.*;
import com.example.sae_zeldalike.modele.Environnement.*;
import com.example.sae_zeldalike.modele.Item.Arc;
import com.example.sae_zeldalike.modele.Item.Flèche;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import com.example.sae_zeldalike.modele.Personnage.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement environnement;
    private Link link;
    private VueLink vueLink;
    private Ennemi1 ennemi1;
    private VueEnnemi1 vueEnnemi1;
    private Map map;
    private VueMap vueMap;
    private ArrayList<Item> items;
    private ArrayList<VueItem> vueItems;
    private Piece item;
    private VueItem vueItem;
    private Personnage personnage;
    @FXML
    private Label nombrePiece;
    @FXML
    private ProgressBar barreDeVie;
    @FXML
    private ImageView imagePerso;
    @FXML
    private Pane pane;
    @FXML
    private TilePane tilePane;
    private Timeline gameLoop;

    private int temps;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            // Changez "path/to/your/map.json" par le chemin réel de votre fichier JSON
            this.map = new Map("src/main/resources/1erTerrain.json");
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer l'erreur de manière appropriée, par exemple en affichant un message d'erreur à l'utilisateur
        }

        this.environnement = new Environnement(map);
        this.link = new Link(environnement, 32, 32);
        this.vueLink = new VueLink(pane, link);
        this.ennemi1 = new Ennemi1(environnement, 130, 220);
        this.vueEnnemi1 = new VueEnnemi1(pane, ennemi1);
        vueMap = new VueMap(tilePane, map);
        this.link.ajouterArme(new Arc(15, 1));
        this.link.ajouterFlèche(new Flèche(this.link.getPositionX(), this.link.getPositionY(),30, this.environnement));

        this.barreDeVie.progressProperty().bind(link.pointViePercentProperty());

        link.getPortefeuilleProperty().addListener((obs, old, nouv)-> this.nombrePiece.setText(nouv.toString()));

        imagePerso.setFitHeight(64);
        imagePerso.setFitWidth(64);
        imagePerso.imageProperty().bind(vueLink.getSpritePersonnage().imageProperty());
        items = new ArrayList();
        vueItems = new ArrayList();
        for (int i = 0; i < 10; i++) {
            this.item = new Piece(environnement);
            this.vueItem = new VueItem(pane, item);
            items.add(item);
            vueItems.add(vueItem);
        }

        initAnimation();

        this.link.getPositionXProperty().addListener((observable, oldValue, newValue) -> {
            this.pane.setTranslateX(50 + pane.getPrefWidth() / 2 - link.getPositionX());

        });
        this.link.getPositionYProperty().addListener((observable, oldValue, newValue) -> {
            this.pane.setTranslateY( pane.getPrefHeight() / 2 - link.getPositionY());
        });
        this.pane.setTranslateX(pane.getPrefWidth() / 2 - link.getPositionX());
        this.pane.setTranslateY(pane.getPrefHeight() / 2 - link.getPositionY());

        // demarre l'animation
        gameLoop.play();


    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                ev -> {
                    if (temps == 1000000) {
                        System.out.println("fini");
                        gameLoop.stop();
                    } else if (temps % 10 == 0) {
                        vueEnnemi1.changerImage();
                        //ennemi1.seDeplace(link);
                        ennemi1.seDeplace(link.getPositionX(), link.getPositionY());
                        if (this.link.getArc().flècheLancée()) {
                            VueFlèche vueFleche = this.link.getArc().getFlèchesEnDéplacement().get(0);
                            Flèche flèche = vueFleche.getFlèche();

                            switch (link.getDirection()) {
                                case "UP":
                                    flèche.seDeplacerHaut();
                                case "DOWN":
                                    flèche.seDeplacerBas();
                                case "RIGHT":
                                    flèche.seDeplacerDroite();
                                case "LEFT":
                                    flèche.seDeplacerGauche();
                                default:

                            }

                            if (flèche.estDevantObstacle(flèche.getX(), flèche.getY()) || flèche.estDansLimiteTerrain(flèche.getX(), flèche.getY())) {
                                Platform.runLater(() -> {
                                    vueFleche.supprimerFlèche(this.pane);
                                    this.link.getArc().getFlèchesEnDéplacement().remove(vueFleche);
                                    System.out.println("Flèche supprimée du pane et de la liste des flèches en déplacement");
                                });
                            }
                        }
                        for (int i = 0; i < items.size(); i++) {
                            vueItems.get(i).animationItem();
                        }
                        vueLink.animationPersonnage();
                    }
                    if (temps % 3 == 0) {
                        this.pane.requestFocus();
                    }

                    temps++;
                }
        );
        gameLoop.getKeyFrames().add(kf);
    }
}

