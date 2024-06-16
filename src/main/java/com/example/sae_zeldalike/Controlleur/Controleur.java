package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.Controlleur.Observateur.*;
import com.example.sae_zeldalike.Vue.*;
import com.example.sae_zeldalike.Vue.Environnement.VueMap;
import com.example.sae_zeldalike.Vue.Item.VueBombe;
import com.example.sae_zeldalike.Vue.Item.VueItem;
import com.example.sae_zeldalike.Vue.Item.VuePiece;
import com.example.sae_zeldalike.Vue.Personnage.*;
import com.example.sae_zeldalike.Vue.Personnage.VueEnnemi1;
import com.example.sae_zeldalike.Vue.Projectile.VueFleche;
import com.example.sae_zeldalike.Vue.Projectile.VueProjectile;
import com.example.sae_zeldalike.modele.Environnement.*;
import com.example.sae_zeldalike.modele.Item.*;
import com.example.sae_zeldalike.modele.Personnage.*;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi1;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi2;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Magicien;
import com.example.sae_zeldalike.modele.Projectile.Fleche;
import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.fxml.FXML;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ArrayList<VueProjectile> vueProjectiles;
    @FXML
    private Label nombrePiece;
    @FXML
    private ImageView imagePerso;
    @FXML
    private HBox emplacementCoeurs;

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
    private ImageView case4;
    @FXML
    private ImageView case5;
    @FXML
    private ImageView case6;


    @FXML
    private StackPane emplacement1;
    @FXML
    private StackPane emplacement2;
    @FXML
    private StackPane emplacement3;
    @FXML
    private StackPane emplacement4;
    @FXML
    private StackPane emplacement5;
    @FXML
    private StackPane emplacement6;

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
        this.link = new Link(environnement);
        this.vueLink = new VueLink(pane, link);
        environnement.ajouterPersonnage(link);
        this.ennemi1 =new Ennemi1(environnement);
        environnement.ajouterPersonnage(ennemi1);
        this.vueEnnemi1 =new VueEnnemi1(pane,ennemi1);

        //Barre de vie Binder en fonction de la vie du personnage
        ObservateurCoeurs observateurCoeurs = new ObservateurCoeurs(emplacementCoeurs, link);
        link.getPointVieProperty().addListener(observateurCoeurs);
        link.getPointDeVieAdditionelleProperty().addListener(observateurCoeurs);

        //Observateur des vuesDesItems
        vueItems = new ArrayList<>();
        ObservateurItem observateurItem = new ObservateurItem(pane,vueItems);
        this.environnement.getItems().addListener(observateurItem);

        //Observateur des vuesPersonnages
        vuePersos = new ArrayList<>();
        this.environnement.getPersonnages().addListener(new ObservateurPersonnage(pane,vuePersos));

        //Observateur des VuesProjectiles
        vueProjectiles=new ArrayList<>();
        this.environnement.getProjectiles().addListener(new ObservateurProjectile(pane,vueProjectiles));

        //Observateur sur l'inventaire de Link
        link.getInventaire().getIndiceChangementProperty().addListener((obs,old,nouv)-> mettreAjourInventaire(nouv.intValue(),observateurItem));

        //Observateur sur l'indice de la case de l'inventaire selectionner
        link.getInventaire().getCaseActuelProperty().addListener(new ObservateurCaseInventaire(emplacement1,emplacement2,emplacement3,emplacement4,emplacement5,emplacement6));

        //Observateur sur la monnaie de Link
        link.getPortefeuilleProperty().addListener((obs, old, nouv)-> this.nombrePiece.setText(nouv.toString()));

        //Initialiser la taille de l'image du perso du coin gauche
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


    }



    private void mettreAjourInventaire(int changement, ObservateurItem observateurItem){
        if (changement!=-1) {
            if (link.getInventaire().getInventaireCase1() != null) {
                for (int i=0;i<observateurItem.getVueItems().size();i++){
                    if (link.getInventaire().getInventaireCase1().getItem().getId().equals(observateurItem.getVueItems().get(i).getSpriteId())){
                        case1.setImage(observateurItem.getVueItems().get(i).getImagePrincipale());
                    }
                }
            }else{
                case1.setImage(null);
            }
            if (link.getInventaire().getInventaireCase2() != null) {
                for (int i=0;i<observateurItem.getVueItems().size();i++){
                    if (link.getInventaire().getInventaireCase2().getItem().getId().equals(observateurItem.getVueItems().get(i).getSpriteId())){
                        case2.setImage(observateurItem.getVueItems().get(i).getImagePrincipale());
                    }
                }
            }else {

                case2.setImage(null);
            }
            if (link.getInventaire().getInventaireCase3() != null) {

                for (int i=0;i<observateurItem.getVueItems().size();i++){
                    if (link.getInventaire().getInventaireCase3().getItem().getId().equals(observateurItem.getVueItems().get(i).getSpriteId())){
                        case3.setImage(observateurItem.getVueItems().get(i).getImagePrincipale());
                    }
                }
            }else {
                case3.setImage(null);
            }
            if (link.getInventaire().getInventaireCase4() != null) {

                for (int i=0;i<observateurItem.getVueItems().size();i++){
                    if (link.getInventaire().getInventaireCase4().getItem().getId().equals(observateurItem.getVueItems().get(i).getSpriteId())){
                        case4.setImage(observateurItem.getVueItems().get(i).getImagePrincipale());
                    }
                }
            }else {
                case4.setImage(null);
            }
            if (link.getInventaire().getInventaireCase5() != null) {

                for (int i=0;i<observateurItem.getVueItems().size();i++){
                    if (link.getInventaire().getInventaireCase5().getItem().getId().equals(observateurItem.getVueItems().get(i).getSpriteId())){
                        case5.setImage(observateurItem.getVueItems().get(i).getImagePrincipale());
                    }
                }
            }else {
                case5.setImage(null);
            }
            if (link.getInventaire().getInventaireCase6() != null) {

                for (int i=0;i<observateurItem.getVueItems().size();i++){
                    if (link.getInventaire().getInventaireCase6().getItem().getId().equals(observateurItem.getVueItems().get(i).getSpriteId())){
                        case6.setImage(observateurItem.getVueItems().get(i).getImagePrincipale());
                    }
                }
            }else {
                case6.setImage(null);
            }
        }
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

//                        if (this.link.getArc().flècheLancée()) {
//                            VueFlèche vueFleche = this.link.getArc().getFlèchesEnDéplacement().get(0);
//                            Flèche flèche = vueFleche.getFlèche();
//
//                            switch (link.getDirection()) {
//                                case "UP":
//                                    flèche.seDeplacerHaut();
//                                case "DOWN":
//                                    flèche.seDeplacerBas();
//                                case "RIGHT":
//                                    flèche.seDeplacerDroite();
//                                case "LEFT":
//                                    flèche.seDeplacerGauche();
//                                default:
//
//                            }
//
//                            if (flèche.estDevantObstacle(flèche.getX(), flèche.getY()) || flèche.estDansLimiteTerrain(flèche.getX(), flèche.getY())) {
//                                Platform.runLater(() -> {
//                                    vueFleche.supprimerFlèche(this.pane);
//                                    this.link.getArc().getFlèchesEnDéplacement().remove(vueFleche);
//                                    System.out.println("Flèche supprimée du pane et de la liste des flèches en déplacement");
//                                });
//                            }
//                        }

                    }
                    else if (temps%4 ==0){
//                        int compteur =0;
//                        for (Personnage ops : environnement.getPersonnages()) {
//                            if (ops instanceof Ennemi1){
//                                compteur++;
//                            }
//                        }
//                        if (compteur<=10){
//                            for (int cpt = 10-compteur;cpt>=0;cpt--){
//                                environnement.ajouterPersonnage(new Ennemi1(environnement));
//                            }
//                        }
                        for (VuePersonnage monPerso : vuePersos){


                            if (monPerso instanceof VueEnnemi1){
                                monPerso.animation();

                                Ennemi1 e1 = (Ennemi1) monPerso.getPersonnage();
                                e1.seDeplace(link.getPositionX()+ link.getLargeur()/4, link.getPositionY()+ link.getLongueur()/4);

                            }
                            if (monPerso instanceof VueEnnemi2) {
                                monPerso.animation();
                                Ennemi2 e2 = (Ennemi2) monPerso.getPersonnage();
                                e2.seDeplace(link.getPositionX()+ link.getLargeur()/4, link.getPositionY()+ link.getLongueur()/4);

                            }
                                if (monPerso instanceof VueMagicien){
                                    monPerso.animation();
                                    Magicien mage = (Magicien) monPerso.getPersonnage();
                                    mage.invocationPersonnage();
                                    mage.seDeplace(link.getPositionX()+ link.getLargeur()/4, link.getPositionY()+ link.getLongueur()/4);


                                }
                        }
                        for (VueProjectile projectile : vueProjectiles){
                            if (projectile instanceof VueFleche){
                                projectile.animationProjectile();

                                Fleche fleche = (Fleche) projectile.getProjectile();
                                fleche.deplacement();
                            }
                        }
                    }
                    if (temps % 9 == 0) {

//                        vueEnnemi1.animation();
//                        ennemi1.seDeplace(link.getPositionX()+ link.getLargeur()/4, link.getPositionY()+ link.getLongueur()/4);
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

