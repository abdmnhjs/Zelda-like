package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.Controlleur.Clavier;
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

    public VueLink(Pane pane, Personnage personnage) {
        this.imageView = new ImageView("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_R.png");
        this.personnage=personnage;
        this.id= personnage.getId();
        this.imageView.setId("#"+personnage.getId());
        this.imageView.setFitHeight(32);
        this.imageView.setFitWidth(32);
        this.imageView.setTranslateX(personnage.getPositionX());
        this.imageView.setTranslateY(personnage.getPositionY());
        pane.getChildren().add(this.imageView);
        Clavier clavier = new Clavier(personnage);
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
