package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.Vue.*;
import com.example.sae_zeldalike.modele.Environnement.*;
import com.example.sae_zeldalike.modele.Personnage.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement environnement;
    private Personnage personnage;
    private VueLink vueLink;
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
        this.personnage = new Link(environnement, 32, 32);
        this.vueLink=new VueLink(pane,personnage);
        vueMap = new VueMap(tilePane,map);
        aRencontréCollision(this.personnage, this.vueLink.getImageView(), vueMap);
        initAnimation();
        // demarre l'animation
        gameLoop.play();
    }

    private boolean collisions(ImageView personnage, VueMap vueMap) {
        double personnageX = personnage.getX();
        double personnageY = personnage.getY();
        double personnageWidth = personnage.getFitWidth();
        double personnageHeight = personnage.getFitHeight();

        for (ImageView obstacle : vueMap.getObstacles()) {
            double obstacleX = obstacle.getX();
            double obstacleY = obstacle.getY();
            double obstacleWidth = obstacle.getFitWidth();
            double obstacleHeight = obstacle.getFitHeight();

            if (personnageX <= obstacleX + obstacleWidth &&
                    personnageWidth >= obstacleX &&
                    personnageY <= obstacleY + obstacleHeight &&
                    personnageY + personnageHeight >= obstacleY) {
                return true;
            }
        }
        return false;
    }

    private void aRencontréCollision(Personnage personnage, ImageView vuePerso, VueMap vueMap) {
        if (collisions(vuePerso, vueMap)) {
            personnage.setPositionXProperty(personnage.getPrécédentePositionXProperty().getValue());
            personnage.setPositionYProperty(personnage.getPrécédentePositionYProperty().getValue());
        }
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

                       gameLoop.stop();
                    }
                    else if (temps%5==0){

                        this.pane.requestFocus();
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}
