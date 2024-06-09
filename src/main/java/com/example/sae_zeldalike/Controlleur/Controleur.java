package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.Controlleur.Observateur.*;
import com.example.sae_zeldalike.Vue.*;
import com.example.sae_zeldalike.Vue.Environnement.VueMap;
import com.example.sae_zeldalike.Vue.Item.VueItem;
import com.example.sae_zeldalike.Vue.Personnage.VueEnnemi1;
import com.example.sae_zeldalike.Vue.Personnage.VueLink;
import com.example.sae_zeldalike.Vue.Personnage.VuePersonnage;
import com.example.sae_zeldalike.modele.Environnement.*;
import com.example.sae_zeldalike.modele.Item.*;
import com.example.sae_zeldalike.modele.Personnage.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
import java.util.ResourceBundle;

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
    private StackPane emplacement1;
    @FXML
    private StackPane emplacement2;
    @FXML
    private StackPane emplacement3;


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
        this.link.equiperArc();

        //Barre de vie Binder en fonction de la vie du personnage
        ObservateurCoeurs observateurCoeurs = new ObservateurCoeurs(emplacementCoeurs, link);
        link.getPointVieProperty().addListener(observateurCoeurs);
        link.getPointDeVieAdditionelleProperty().addListener(observateurCoeurs);

//        this.nombrePiece.textProperty().bind(link.getPortefeuilleProperty().asString());
        link.getPortefeuilleProperty().addListener((obs, old, nouv)-> this.nombrePiece.setText(nouv.toString()));

        //Observateur des vuesDesItems
        vueItems = new ArrayList<>();
        ObservateurItem observateurItem = new ObservateurItem(pane,vueItems);
        this.environnement.getItems().addListener(observateurItem);

        //Observateur des vuesPersonnages
        vuePersos = new ArrayList<>();
        vueFlèches = new ArrayList<>();
        this.environnement.getItems().addListener(new ObservateurItem(pane, vueItems));
        this.environnement.getFlèchesEnDéplacement().addListener(new ObservateurFlechesEnDeplacement(pane));
        this.environnement.getPersonnages().addListener(new ObservateurEnnemi1(pane, vuePersos));
        this.environnement.getEpeeEnMain().addListener(new ObservateurEpee(pane, this.link));
        this.environnement.getPersonnages().addListener(new ObservateurEnnemi1(pane,vuePersos));
        this.environnement.getLinkRemovalQueue().addListener(new ObservateurLink(pane));
        this.environnement.ajouterLink(this.link);


        //Observateur sur l'inventaire de Link
        link.getInventaire().getIndiceChangementProperty().addListener((obs,old,nouv)-> mettreAjourInventaire(nouv.intValue(),observateurItem));

        //Observateur sur l'indice de la case de l'inventaire selectionné

        link.getInventaire().getCaseActuelProperty().addListener(new ObservateurCaseInventaire(emplacement1,emplacement2,emplacement3));


        //Observateur sur la monnaie de Link
        link.getPortefeuilleProperty().addListener((obs, old, nouv)-> this.nombrePiece.setText(nouv.toString()));

        //Initialiser la taille de l'image du perso du coin gauche
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
                System.out.println("case3 vide");
                case3.setImage(null);
            }
        }
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

                        for(Ennemi ennemi : this.environnement.getPersonnages()){
                            if(ennemi.estSurJoueur(this.link)){
                                ennemi.essaieAttaquerJoueur(this.link);
                            }
                        }

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
                            ArrayList<Projectile> flechesASupprimer = new ArrayList<>();

                            for (int i = 0 ; i < this.environnement.getFlèchesEnDéplacement().size() ; i++) {
                                int newX;
                                int newY;
                                Projectile projectile = this.environnement.getFlèchesEnDéplacement().get(i);


                                if(projectile.getDirection().equals("UP")){
                                    newX = projectile.getX();
                                    newY = projectile.getY() - projectile.getVitesse();

                                        if (projectile.getY() > projectile.getInitialY() - link.getArc().getRayonAttaque()) {
                                            tempsRechargeFleche = 3000;
                                                projectile.setyProperty(newY);
                                            } else {
                                                projectile.getEnvironnement().supprimerFleche(projectile);
                                            }
                                    }

                                if(projectile.getDirection().equals("DOWN")){
                                    newX = projectile.getX();
                                    newY = projectile.getY() + projectile.getVitesse();
                                        if (projectile.getY() < projectile.getInitialY() + link.getArc().getRayonAttaque()) {
                                            tempsRechargeFleche = 3000;
                                            projectile.setyProperty(newY);
                                        } else {
                                            flechesASupprimer.add(projectile);
                                        }

                                    }
                                if(projectile.getDirection().equals("RIGHT")){
                                    newX = projectile.getX() + projectile.getVitesse();
                                    newY = projectile.getY();
                                        if (projectile.getX() < projectile.getInitialX() + link.getArc().getRayonAttaque()) {
                                            tempsRechargeFleche = 3000;
                                            projectile.setxProperty(newX);
                                        } else {
                                            flechesASupprimer.add(projectile);
                                        }

                                    }
                                if(projectile.getDirection().equals("LEFT")){
                                    newX = projectile.getX() - projectile.getVitesse();
                                    newY = projectile.getY();
                                        if (projectile.getX() > projectile.getInitialX() - link.getArc().getRayonAttaque()) {
                                            tempsRechargeFleche = 3000;
                                            projectile.setxProperty(newX);
                                        } else {
                                            flechesASupprimer.add(projectile);
                                        }


                                }

                                for (int j = 0 ; j < this.environnement.getPersonnages().size() ; j++) {
                                    if (projectile.estSurEnnemi(this.environnement.getPersonnages().get(j))) {
                                        projectile.faireDégâts(this.environnement.getPersonnages().get(j), projectile.getDégâts());
                                        projectile.getEnvironnement().supprimerFleche(projectile);
                                        flechesASupprimer.add(projectile);
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



                    tempsRechargeFleche--;
                    temps++;
                }
        );
        gameLoop.getKeyFrames().add(kf);
    }
}

