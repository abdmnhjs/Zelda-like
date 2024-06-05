package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.Controlleur.Observateur.ObservateurCaseInventaire;
import com.example.sae_zeldalike.Controlleur.Observateur.ObservateurInventaire;
import com.example.sae_zeldalike.Controlleur.Observateur.ObservateurItem;
import com.example.sae_zeldalike.Controlleur.Observateur.ObservateurPersonnage;
import com.example.sae_zeldalike.Vue.*;
import com.example.sae_zeldalike.Vue.Environnement.VueMap;
import com.example.sae_zeldalike.Vue.Item.VueBombe;
import com.example.sae_zeldalike.Vue.Item.VueItem;
import com.example.sae_zeldalike.Vue.Item.VuePiece;
import com.example.sae_zeldalike.Vue.Personnage.VueEnnemi1;
import com.example.sae_zeldalike.Vue.Personnage.VueLink;
import com.example.sae_zeldalike.Vue.Personnage.VuePersonnage;
import com.example.sae_zeldalike.modele.Environnement.*;
import com.example.sae_zeldalike.modele.Item.*;
import com.example.sae_zeldalike.modele.Personnage.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.fxml.FXML;
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

    private ArrayList<VueItem> vueItems;
    private ArrayList<VuePersonnage> vuePersos;

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

    @FXML
    private ImageView case1;
    @FXML
    private ImageView case2;
    @FXML
    private ImageView case3;

    @FXML
    private StackPane emplacement1;
    @FXML
    private StackPane emplacement2;
    @FXML
    private StackPane emplacement3;


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
        vueMap = new VueMap(tilePane, map);
        this.link = new Link(environnement, 32, 32);
        this.vueLink = new VueLink(pane, link);
        this.ennemi1 =new Ennemi1(environnement);
        environnement.ajouterPersonnage(ennemi1);
        this.vueEnnemi1 =new VueEnnemi1(pane,ennemi1);

        this.link.ajouterArme(new Arc(15, 1));
        this.link.ajouterFlèche(new Flèche(this.link.getPositionX(), this.link.getPositionY(),30, this.environnement));

        this.barreDeVie.progressProperty().bind(link.pointViePercentProperty());

        vueItems = new ArrayList<>();
        this.environnement.getItems().addListener(new ObservateurItem(pane,vueItems));

        vuePersos = new ArrayList<>();
        this.environnement.getPersonnages().addListener(new ObservateurPersonnage(pane,vuePersos));

        imagePerso.setFitHeight(64);
        imagePerso.setFitWidth(64);
        imagePerso.maxWidth(64);
        imagePerso.maxHeight(64);

        imagePerso.imageProperty().bind(vueLink.getSpritePersonnage().imageProperty());

        environnement.init();

        initAnimation();

        // Scroll Map
        this.link.getPositionXProperty().addListener((observable, oldValue, newValue) -> {
            this.pane.setTranslateX( pane.getPrefWidth() / 2 - link.getPositionX()-(link.getLargeur()/2));
        });
        this.link.getPositionYProperty().addListener((observable, oldValue, newValue) -> {
            this.pane.setTranslateY( pane.getPrefHeight() / 2 - link.getPositionY()-(link.getLongueur()/2));
        });
        this.pane.setTranslateX(pane.getPrefWidth() / 2 - link.getPositionX()-(link.getLargeur()/2));
        this.pane.setTranslateY(pane.getPrefHeight() /2 - link.getPositionY()-(link.getLongueur()/2));

        // demarre l'animation
        gameLoop.play();

        link.getInventaire().addListener(new ObservateurInventaire(case1,case2,case3));
        link.getNumeroCaseActuelProperty().addListener(new ObservateurCaseInventaire(emplacement1,emplacement2,emplacement3));


        link.getPortefeuilleProperty().addListener((obs, old, nouv)-> this.nombrePiece.setText(nouv.toString()));

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

                        vueLink.animation();

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


                    }
                    else if (temps%4 ==0){
                        for (VuePersonnage monPerso : vuePersos){
                            if (monPerso instanceof VueEnnemi1){
                                monPerso.animation();

                                Ennemi1 e1 = (Ennemi1) monPerso.getPersonnage();
                                e1.seDeplace(link.getPositionX()+ link.getLargeur()/4, link.getPositionY()+ link.getLongueur()/4);
                            }
                        }
                    }
                    if (temps % 9 == 0) {

                        vueEnnemi1.animation();
                        ennemi1.seDeplace(link.getPositionX()+ link.getLargeur()/4, link.getPositionY()+ link.getLongueur()/4);
                        this.pane.requestFocus();
                        for (VueItem monItem : vueItems){
                            monItem.animationItem();

                        }
                    }

                    temps++;
                }
        );
        gameLoop.getKeyFrames().add(kf);
    }
}

