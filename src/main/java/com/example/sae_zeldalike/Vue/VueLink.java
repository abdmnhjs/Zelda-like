package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.Controlleur.Clavier;
import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class VueLink {

    private Link link;
    private ImageView spritePersonnage;
    private int numeroImagePersonnage;
    private StringProperty direction;
    private Clavier clavier;
    private Rectangle rectangle;

    public VueLink(Pane pane, Link link, Environnement environnement) {

        this.clavier = new Clavier(link, pane, environnement);
        pane.addEventFilter(KeyEvent.KEY_PRESSED,clavier);

        this.link=link;
        direction = new SimpleStringProperty();
        direction.bind(link.getDirectionProperty());

        creerPerso(pane);


    }

    public void creerPerso(Pane pane){
        this.numeroImagePersonnage=1;
        this.spritePersonnage = new ImageView("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_R.png");
        this.spritePersonnage.setId("#"+ link.getId());
        this.spritePersonnage.setFitHeight(32);
        this.spritePersonnage.setFitWidth(32);
        this.spritePersonnage.setTranslateX(link.getPositionX());
        this.spritePersonnage.setTranslateY(link.getPositionY());
        pane.getChildren().add(this.spritePersonnage);

        spritePersonnage.translateXProperty().bind(link.getPositionXProperty());
        spritePersonnage.translateYProperty().bind(link.getPositionYProperty());
    }



    public void animationPersonnage() {
        switch (getDirection()){
            case "UP"-> {
                if (numeroImagePersonnage==1){
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/UP_R.png"));
                    setNumeroImagePersonnage(2);
                } else if (numeroImagePersonnage==2) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/UP_L.png"));
                    setNumeroImagePersonnage(1);
                }
            }
            case "DOWN"-> {
                if (numeroImagePersonnage==1) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_R.png"));
                    setNumeroImagePersonnage(2);
                } else if (numeroImagePersonnage==2) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_L.png"));
                    setNumeroImagePersonnage(1);
                }
            }
            case "LEFT"->{
                if (numeroImagePersonnage==1) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_R.png"));
                    setNumeroImagePersonnage(2);
                } else if (numeroImagePersonnage==2) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_L.png"));
                    setNumeroImagePersonnage(1);
                }
            }
            case "RIGHT"->{
                if (numeroImagePersonnage==1) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_R.png"));
                    setNumeroImagePersonnage(2);
                } else if (numeroImagePersonnage==2) {
                    spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_L.png"));
                    setNumeroImagePersonnage(1);
                }
            }

        }
    }

    public StringProperty getDirectionProperty() {
        return direction;
    }
    public String getDirection(){
        return direction.getValue();
    }

    public void setDirectionProperty(String direction) {
        this.direction.setValue(direction);
    }

    public void setNumeroImagePersonnage(int numeroImagePersonnage) {
        this.numeroImagePersonnage = numeroImagePersonnage;
    }


    public Personnage getLink() {
        return link;
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
