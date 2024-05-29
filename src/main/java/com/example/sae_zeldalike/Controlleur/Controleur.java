package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.Vue.*;
import com.example.sae_zeldalike.modele.Environnement.*;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import com.example.sae_zeldalike.modele.Personnage.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement environnement;
    private Link personnage;
    private VueLink vueLink;
    private Ennemi1 ennemi1;
    private VueEnnemi1 vueEnnemi1;
    private Map map;
    private VueMap vueMap;
    private ArrayList<Item> items;
    private ArrayList<VueItem> vueItems;
    private Piece item;
    private VueItem vueItem;

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
        this.map= new Map();
        this.environnement = new Environnement(map);
        this.personnage = new Link(environnement, 32, 32);
        this.vueLink=new VueLink(pane,personnage);
        this.ennemi1=new Ennemi1(environnement,130,220);
        this.vueEnnemi1 = new VueEnnemi1(pane,ennemi1);
        vueMap = new VueMap(tilePane,map);
        this.barreDeVie.progressProperty().bind(personnage.pointViePercentProperty());
//        personnage.getPieces().addListener((obs,old,nouv)->new );

        imagePerso.setFitHeight(64);
        imagePerso.setFitWidth(64);
        imagePerso.imageProperty().bind(vueLink.getSpritePersonnage().imageProperty());
        items = new ArrayList();
        vueItems = new ArrayList();
        for (int i=0;i<2;i++) {
            this.item = new Piece(environnement);
            this.vueItem = new VueItem(pane, item);
            items.add(item);
            vueItems.add(vueItem);
        }

        initAnimation();
        // demarre l'animation
        gameLoop.play();


    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps==1000000){
                        System.out.println("fini");
                       gameLoop.stop();
                    } else if (temps%10==0) {
                        vueEnnemi1.changerImage();
                        //ennemi1.seDeplace(link);
                        ennemi1.seDeplace(personnage.getPositionX(), personnage.getPositionY());
                        for (int i=0;i<items.size();i++) {
                            vueItems.get(i).animationItem();
                        }

                        vueLink.animationPersonnage();
                    } else if (temps%3==0){

//                        System.out.println(environnement.getMap().getValeurTuile(item.getPositionX(), item.getPositionY()));

                        this.pane.requestFocus();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}
