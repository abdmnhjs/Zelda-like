package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.Controlleur.Clavier;
import com.example.sae_zeldalike.modele.Environnement.Map;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class VueLink {

    private Personnage personnage;
    private ImageView imageView;
    private String id;
    private Map map;

    public VueLink(Pane pane, Personnage personnage) {
        this.imageView = new ImageView("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_R.png");
        this.personnage=personnage;
        this.id= personnage.getId();
        this.imageView.setId("#"+personnage.getId());
        this.imageView.setFitHeight(personnage.getLongueur());
        this.imageView.setFitWidth(personnage.getLargeur());
        this.imageView.setTranslateX(personnage.getPositionX());
        this.imageView.setTranslateY(personnage.getPositionY());
        pane.getChildren().add(this.imageView);
        this.map = new Map();
        Clavier clavier = new Clavier(personnage, this.map);
        pane.addEventFilter(KeyEvent.KEY_PRESSED,clavier);
        imageView.translateXProperty().bind(personnage.getPositionXProperty());
        imageView.translateYProperty().bind(personnage.getPositionYProperty());
    }

    public String getId() {
        return id;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean estDevantObstacle(int x, int y){
        for(int i = 0 ; i < this.map.getMap().length ; i++){
            if((x/32) == i &&
                    (y/32) == i &&
                    ((x+this.imageView.getFitWidth()/32) == i &&
                    ((y+this.imageView.getFitHeight()/32) == i) &&
                            ((y+this.imageView.getFitHeight()+x+this.imageView.getFitHeight()/32) == i))){
                if(this.map.getMap()[i] == 54){
                    System.out.println("collision");
                    return true;
                }
            }
        }

        return false;
    }

    //    public VueLink(Pane pane, Personnage personnage){
//        Circle circle = new Circle(16);
//        circle.setFill(Color.BLACK);
//        circle.setId(personnage.getId());
//        circle.setTranslateX(personnage.getPositionX());
//        circle.setTranslateY(personnage.getPositionY());
//        pane.getChildren().add(circle);
//        Clavier clavier = new Clavier(personnage);
//        pane.addEventFilter(KeyEvent.KEY_PRESSED,clavier);
//        circle.translateXProperty().bind(personnage.getPositionXProperty());
//        circle.translateYProperty().bind(personnage.getPositionYProperty());
//    }
}
