package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.Controlleur.Clavier;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;



public class VueLink {

    private Personnage personnage;
    private ImageView spritePersonnage;
    private int numeroImagePersonnage;
    private StringProperty direction;
    private Clavier clavier;

    public VueLink(Pane pane, Personnage personnage) {
        this.clavier = new Clavier(personnage);
        pane.addEventFilter(KeyEvent.KEY_PRESSED,clavier);


        this.numeroImagePersonnage=1;
        this.spritePersonnage = new ImageView("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_R.png");
        this.personnage=personnage;
        this.spritePersonnage.setId("#"+personnage.getId());
        this.spritePersonnage.setFitHeight(32);
        this.spritePersonnage.setFitWidth(32);
        this.spritePersonnage.setTranslateX(personnage.getPositionX());
        this.spritePersonnage.setTranslateY(personnage.getPositionY());
        pane.getChildren().add(this.spritePersonnage);
        direction = new SimpleStringProperty();
        direction.bind(personnage.getDirectionProperty());

        spritePersonnage.translateXProperty().bind(personnage.getPositionXProperty());
        spritePersonnage.translateYProperty().bind(personnage.getPositionYProperty());
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
