package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.Vue.*;
import com.example.sae_zeldalike.modele.Environnement.*;
import com.example.sae_zeldalike.modele.Item.Arc;
import com.example.sae_zeldalike.modele.Item.Arme;
import com.example.sae_zeldalike.modele.Item.Flèche;
import com.example.sae_zeldalike.modele.Personnage.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.fxml.FXML;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement environnement;
    private Link link;
    private VueLink vueLink;
    private Ennemi1 ennemi1;
    private VueEnnemi1 vueEnnemi1;
    private Map map;
    private VueMap vueMap;

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
        this.link = new Link(environnement, 32, 32);
        this.link.ajouterArme(new Arc(15, 1));
        this.link.ajouterFlèche(new Flèche(this.link.getPositionX(), this.link.getPositionY(), 5, 10, this.environnement));
        this.vueLink=new VueLink(pane, link, environnement);
        this.ennemi1=new Ennemi1(environnement,130,220);
        this.vueEnnemi1 = new VueEnnemi1(pane,ennemi1);
        vueMap = new VueMap(tilePane,map);
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
                        ennemi1.seDeplace(link.getPositionX(), link.getPositionY());
                        vueLink.animationPersonnage();
                    } else if (temps%3==0){
                        this.pane.requestFocus();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}
