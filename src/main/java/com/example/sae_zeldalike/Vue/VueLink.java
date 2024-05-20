package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.Controlleur.Clavier;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;



public class VueLink {

    private Personnage personnage;
    private ImageView spritePersonnage;
    private String id;

    public VueLink(Pane pane, Personnage personnage) {
        this.spritePersonnage = new ImageView("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_R.png");
        this.personnage=personnage;
        this.id= personnage.getId();
        this.spritePersonnage.setId("#"+getId());
        this.spritePersonnage.setFitHeight(32);
        this.spritePersonnage.setFitWidth(32);
        this.spritePersonnage.setTranslateX(personnage.getPositionX());
        this.spritePersonnage.setTranslateY(personnage.getPositionY());
        pane.getChildren().add(this.spritePersonnage);
        Clavier clavier = new Clavier(personnage);
        pane.addEventFilter(KeyEvent.KEY_PRESSED,clavier);
        spritePersonnage.translateXProperty().bind(personnage.getPositionXProperty());
        spritePersonnage.translateYProperty().bind(personnage.getPositionYProperty());
    }

    public String getId() {
        return id;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public ImageView getSpritePersonnage() {
        return spritePersonnage;
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
