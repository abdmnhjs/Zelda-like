package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.Controlleur.Observateur.ObservateurEpee;
import com.example.sae_zeldalike.Controlleur.Observateur.ObservateurFlechesEnDeplacement;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controleur implements Initializable {

    private Environnement environnement;
    private Link link;
    private VueLink vueLink;
    private Ennemi1 ennemi1;
    private VueEnnemi1 vueEnnemi1;
    private Map map;
    private VueMap vueMap;
    private VueEpee vueEpee;


    private ArrayList<VueItem> vueItems;
    private ArrayList<VuePersonnage> vuePersos;
    private ArrayList<VueFlèche> vueFlèches;



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
    private Clavier clavier;

    private int temps;
    private int tempsRechargeFleche;

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
        this.ennemi1 =new Ennemi1(this.environnement);
        environnement.ajouterPersonnage(ennemi1);
        this.vueEnnemi1 =new VueEnnemi1(pane,ennemi1);
        this.ennemi1 = new Ennemi1(this.environnement, 130, 220);
        this.vueEnnemi1 = new VueEnnemi1(pane, ennemi1);
        vueMap = new VueMap(tilePane, this.map);
        this.link.ajouterEpee(new Epée(link.getPositionX()+link.getLargeur(), link.getPositionY()+16, 50, 5, this.environnement));
        this.link.ajouterArc(new Arc(15, 100, this.environnement));
        this.link.equiperEpee();

        this.barreDeVie.progressProperty().bind(link.pointViePercentProperty());

//        this.nombrePiece.textProperty().bind(link.getPortefeuilleProperty().asString());
        link.getPortefeuilleProperty().addListener((obs, old, nouv)-> this.nombrePiece.setText(nouv.toString()));

        vueItems = new ArrayList<>();
        this.environnement.getItems().addListener(new ObservateurItem(pane,vueItems));

        vuePersos = new ArrayList<>();
        vueFlèches = new ArrayList<>();
        this.environnement.getItems().addListener(new ObservateurItem(pane, vueItems));
        this.environnement.getFlèchesEnDéplacement().addListener(new ObservateurFlechesEnDeplacement(pane));
        this.environnement.getPersonnages().addListener(new ObservateurPersonnage(pane, vuePersos));
        this.environnement.getEpeeEnMain().addListener(new ObservateurEpee(pane, this.link));
        imagePerso.setFitHeight(64);
        imagePerso.setFitWidth(64);
        imagePerso.maxWidth(64);
        imagePerso.maxHeight(64);
        imagePerso.imageProperty().bind(vueLink.getSpritePersonnage().imageProperty());
        this.clavier = new Clavier(this.link, this.pane, this.environnement);
        environnement.init();
//        items = new ArrayList();
//        vueItems = new ArrayList();
//        for (int i = 0; i < 10; i++) {
//            this.item = new Piece(environnement);
//            this.vueItem = new VueItem(pane, item);
//            items.add(item);
//            vueItems.add(vueItem);
//        }

        initAnimation();

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


    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        tempsRechargeFleche = 0;
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
                        this.clavier.interactionTouche();

                        if(this.link.epeeEquipee()){
                            for(int i = 0 ; i < this.environnement.getEpeeEnMain().size() ; i++){
                                Epée epée = environnement.getEpeeEnMain().get(i);
                                for(int j = 0 ; j < this.environnement.getPersonnages().size() ; j++){

                                    Personnage ennemi = this.environnement.getPersonnages().get(j);

                                    if(epée.estSurEnnemi(ennemi)){
                                        epée.faireDégâts(ennemi, epée.getDégâts());
                                        epée.getEnvironnement().supprimerEpee(epée);
                                    } else {
                                        epée.getEnvironnement().supprimerEpee(epée);
                                    }
                            }

                            }


                        }

                        if(this.link.arcEquipe()){
                            ArrayList<Flèche> flechesASupprimer = new ArrayList<>();

                            for (int i = 0 ; i < this.environnement.getFlèchesEnDéplacement().size() ; i++) {
                                int newX;
                                int newY;
                                Flèche flèche = this.environnement.getFlèchesEnDéplacement().get(i);


                                if(flèche.getDirection().equals("UP")){
                                    newX = flèche.getX();
                                    newY = flèche.getY() - flèche.getVitesse();

                                        if (flèche.getY() > flèche.getInitialY() - link.getArc().getRayonAttaque()) {
                                            tempsRechargeFleche = 3000;
                                                flèche.setyProperty(newY);
                                            } else {
                                                flèche.getEnvironnement().supprimerFleche(flèche);
                                            }
                                    }

                                if(flèche.getDirection().equals("DOWN")){
                                    newX = flèche.getX();
                                    newY = flèche.getY() + flèche.getVitesse();
                                        if (flèche.getY() < flèche.getInitialY() + link.getArc().getRayonAttaque()) {
                                            tempsRechargeFleche = 3000;
                                            flèche.setyProperty(newY);
                                        } else {
                                            flechesASupprimer.add(flèche);
                                        }

                                    }
                                if(flèche.getDirection().equals("RIGHT")){
                                    newX = flèche.getX() + flèche.getVitesse();
                                    newY = flèche.getY();
                                        if (flèche.getX() < flèche.getInitialX() + link.getArc().getRayonAttaque()) {
                                            tempsRechargeFleche = 3000;
                                            flèche.setxProperty(newX);
                                        } else {
                                            flechesASupprimer.add(flèche);
                                        }

                                    }
                                if(flèche.getDirection().equals("LEFT")){
                                    newX = flèche.getX() - flèche.getVitesse();
                                    newY = flèche.getY();
                                        if (flèche.getX() > flèche.getInitialX() - link.getArc().getRayonAttaque()) {
                                            tempsRechargeFleche = 3000;
                                            flèche.setxProperty(newX);
                                        } else {
                                            flechesASupprimer.add(flèche);
                                        }


                                }

                                for (int j = 0 ; j < this.environnement.getPersonnages().size() ; j++) {
                                    if (flèche.estSurEnnemi(this.environnement.getPersonnages().get(j))) {
                                        flèche.faireDégâts(this.environnement.getPersonnages().get(j), flèche.getDégâts());
                                        flèche.getEnvironnement().supprimerFleche(flèche);
                                        flechesASupprimer.add(flèche);
                                    }
                                }
                            }
                            for (int i = 0 ; i < flechesASupprimer.size() ; i++) {
                                this.environnement.supprimerFleche(flechesASupprimer.get(i));
                            }

                        }


                        }

                    else if (temps%4 ==0){
                        for (VuePersonnage monPerso : vuePersos){
                            if (monPerso instanceof VueEnnemi1){
                                monPerso.animation();

                                Ennemi1 e1 = (Ennemi1) monPerso.getPersonnage();
                                e1.seDeplace(link.getPositionX()+ link.getLargeur()/2, link.getPositionY()+ link.getLongueur()/2);
                            }
                        }
                    }
                    if (temps % 9 == 0) {

                        vueEnnemi1.animation();
                        ennemi1.seDeplace(link.getPositionX()+ link.getLargeur()/2, link.getPositionY()+ link.getLongueur()/2);
                        this.pane.requestFocus();
                        for (VueItem monItem : vueItems){
                            monItem.animationItem();

                        }
                    }



                    tempsRechargeFleche--;
                    temps++;
                }
        );
        gameLoop.getKeyFrames().add(kf);
    }
}

