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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class VueLink {

    private Link personnage;
    private ImageView spritePersonnage;
    private int numeroImagePersonnage;
    private StringProperty direction;
    private Clavier clavier;
    private Circle ombre;

    public VueLink(Pane pane, Link personnage ) {

        this.clavier = new Clavier(personnage, pane, personnage.getEnvironnement());
        pane.addEventFilter(KeyEvent.KEY_PRESSED,clavier);
        pane.addEventFilter(KeyEvent.KEY_RELEASED, clavier);

        this.personnage=personnage;
        direction = new SimpleStringProperty();
        direction.bind(personnage.getDirectionProperty());

        creerPerso(pane);



    }

    private void creerPerso(Pane pane){
        this.numeroImagePersonnage=1;
        this.spritePersonnage = new ImageView("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_R.png");
        this.spritePersonnage.setId("#"+personnage.getId());
        this.spritePersonnage.setFitHeight(32);
        this.spritePersonnage.setFitWidth(32);
        this.spritePersonnage.setTranslateX(personnage.getPositionX());
        this.spritePersonnage.setTranslateY(personnage.getPositionY());


        spritePersonnage.translateXProperty().bind(personnage.getPositionXProperty());
        spritePersonnage.translateYProperty().bind(personnage.getPositionYProperty());

        creerOmbre(pane);
        pane.getChildren().add(this.spritePersonnage);


    }
    private void creerOmbre(Pane pane) {
        ombre = new Circle((personnage.getLargeur()/2.5));
        ombre.setFill(Color.GREY);
        ombre.setId("O"+personnage.getId());



        pane.getChildren().add(this.ombre);

        ombre.centerXProperty().bind(spritePersonnage.translateXProperty().add(personnage.getLargeur()/2));
        ombre.centerYProperty().bind(spritePersonnage.translateYProperty().add(personnage.getLongueur()));
    }



    public void animationPersonnage() {
        switch (getDirection()){
            case "UP"-> {
                if (numeroImagePersonnage==1 || numeroImagePersonnage!=2){
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/UP_R.png"));
                    setNumeroImagePersonnage(2);
                } else if (numeroImagePersonnage==2) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/UP_L.png"));
                    setNumeroImagePersonnage(1);
                }
            }
            case "DOWN"-> {
                if (numeroImagePersonnage==1|| numeroImagePersonnage!=2) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_R.png"));
                    setNumeroImagePersonnage(2);
                } else if (numeroImagePersonnage==2) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_L.png"));
                    setNumeroImagePersonnage(1);
                }
            }
            case "LEFT"->{
                if (numeroImagePersonnage==1) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_1.png"));
                    setNumeroImagePersonnage(2);
                }else if (numeroImagePersonnage==2) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_2.png"));
                    setNumeroImagePersonnage(3);
                }else if (numeroImagePersonnage==3) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_3.png"));
                    setNumeroImagePersonnage(4);
                }else if (numeroImagePersonnage==4) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_4.png"));
                    setNumeroImagePersonnage(5);
                }else if (numeroImagePersonnage==5) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_5.png"));
                    setNumeroImagePersonnage(6);
                }else if (numeroImagePersonnage==6) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_6.png"));
                    setNumeroImagePersonnage(7);
                }else if (numeroImagePersonnage==7) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_7.png"));
                    setNumeroImagePersonnage(8);
                }else if (numeroImagePersonnage==8) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_8.png"));
                    setNumeroImagePersonnage(9);
                }else if (numeroImagePersonnage==9) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_9.png"));
                    setNumeroImagePersonnage(1);
                }
            }
            case "RIGHT"->{
                if (numeroImagePersonnage==1) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_1.png"));
                    setNumeroImagePersonnage(2);
                }else if (numeroImagePersonnage==2) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_2.png"));
                    setNumeroImagePersonnage(3);
                }else if (numeroImagePersonnage==3) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_3.png"));
                    setNumeroImagePersonnage(4);
                }else if (numeroImagePersonnage==4) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_4.png"));
                    setNumeroImagePersonnage(5);
                }else if (numeroImagePersonnage==5) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_5.png"));
                    setNumeroImagePersonnage(6);
                }else if (numeroImagePersonnage==6) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_6.png"));
                    setNumeroImagePersonnage(7);
                }else if (numeroImagePersonnage==7) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_7.png"));
                    setNumeroImagePersonnage(8);
                }else if (numeroImagePersonnage==8) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_8.png"));
                    setNumeroImagePersonnage(9);
                }else if (numeroImagePersonnage==9) {
                    this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_9.png"));
                    setNumeroImagePersonnage(1);
                }
            }
            case "Inactif_UP"->{
                this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_UP.png"));
            }
            case "Inactif_DOWN"->{
                this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_DOWN.png"));
            }
            case "Inactif_LEFT"->{
                this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_LEFT.png"));
            }
            case "Inactif_RIGHT"->{
                this.spritePersonnage.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_RIGHT.png"));
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
