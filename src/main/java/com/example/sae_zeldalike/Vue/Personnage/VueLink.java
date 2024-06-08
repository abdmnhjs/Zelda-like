package com.example.sae_zeldalike.Vue.Personnage;

import com.example.sae_zeldalike.Controlleur.Clavier;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class VueLink extends VuePersonnage {


    private Image up1,up2,down1,down2,left1,left2,left3,left4,left5,left6,left7,left8,left9,
    right1,right2,right3,right4,right5,right6,right7,right8,right9,inactifUp1,inactifDown1,inactifDown2,inactifDown3,
            inactifLeft1,inactifLeft2,inactifLeft3,inactifRight1,inactifRight2,inactifRight3;
    private StringProperty direction;
    private Clavier clavier;


    public VueLink(Pane pane, Personnage personnage ) {

        super(pane, personnage);
        this.clavier = new Clavier((Link)personnage, pane, personnage.getEnvironnement());
        pane.addEventFilter(KeyEvent.KEY_PRESSED,clavier);
        pane.addEventFilter(KeyEvent.KEY_RELEASED, clavier);

        intialiserImage();
        this.spritePersonnage.setImage(down1);

        spritePersonnage.translateXProperty().bind(personnage.getPositionXProperty());
        spritePersonnage.translateYProperty().bind(personnage.getPositionYProperty());

        creerOmbre(pane);
        pane.getChildren().add(this.spritePersonnage);
        direction = new SimpleStringProperty();
        direction.bind(personnage.getDirectionProperty());


    }

    private void intialiserImage(){
        this.up1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/UP_R.png");
        this.up2 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/UP_L.png");
        this.down1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_R.png");
        this.down2 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/DOWN_L.png");
        this.left1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_1.png");
        this.left2 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_2.png");
        this.left3 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_3.png");
        this.left4 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_4.png");
        this.left5 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_5.png");
        this.left6 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_6.png");
        this.left7 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_7.png");
        this.left8 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_8.png");
        this.left9 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/LEFT_9.png");
        this.right1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_1.png");
        this.right2 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_2.png");
        this.right3 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_3.png");
        this.right4 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_4.png");
        this.right5 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_5.png");
        this.right6 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_6.png");
        this.right7 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_7.png");
        this.right8 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_8.png");
        this.right9 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/RIGHT_9.png");
        this.inactifDown1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_DOWN.png");
        this.inactifDown2 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_DOWN_2.png");
        this.inactifDown3 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_DOWN_3.png");
        this.inactifUp1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_UP.png");
        this.inactifLeft1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_LEFT.png");
        this.inactifLeft2 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_LEFT_2.png");
        this.inactifLeft3 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_LEFT_3.png");
        this.inactifRight1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_RIGHT.png");
        this.inactifRight2 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_RIGHT_2.png");
        this.inactifRight3 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Link/Inactif_RIGHT_3.png");

    }



    public void creerOmbre(Pane pane) {
        ombre = new Circle((personnage.getLargeur()/2.5));
        ombre.setFill(Color.GREY);
        ombre.setId("O"+personnage.getId());

        pane.getChildren().add(this.ombre);

        ombre.centerXProperty().bind(spritePersonnage.translateXProperty().add(personnage.getLargeur()/2));
        ombre.centerYProperty().bind(spritePersonnage.translateYProperty().add(personnage.getLongueur()-5));


    }


    public void animation() {
        switch (getDirection()){
            case "UP"-> {
                if (numeroImagePersonnage==1 || numeroImagePersonnage!=2){
                    this.spritePersonnage.setImage(up1);
                    setNumeroImagePersonnage(2);
                } else if (numeroImagePersonnage==2) {
                    this.spritePersonnage.setImage(up2);
                    setNumeroImagePersonnage(1);
                }
            }
            case "DOWN"-> {
                if (numeroImagePersonnage==1|| numeroImagePersonnage!=2) {
                    this.spritePersonnage.setImage(down1);
                    setNumeroImagePersonnage(2);
                } else if (numeroImagePersonnage==2) {
                    this.spritePersonnage.setImage(down2);
                    setNumeroImagePersonnage(1);
                }
            }
            case "LEFT"->{
                if (numeroImagePersonnage==1) {
                    this.spritePersonnage.setImage(left1);
                    setNumeroImagePersonnage(2);
                }else if (numeroImagePersonnage==2) {
                    this.spritePersonnage.setImage(left2);
                    setNumeroImagePersonnage(3);
                }else if (numeroImagePersonnage==3) {
                    this.spritePersonnage.setImage(left3);
                    setNumeroImagePersonnage(4);
                }else if (numeroImagePersonnage==4) {
                    this.spritePersonnage.setImage(left4);
                    setNumeroImagePersonnage(5);
                }else if (numeroImagePersonnage==5) {
                    this.spritePersonnage.setImage(left5);
                    setNumeroImagePersonnage(6);
                }else if (numeroImagePersonnage==6) {
                    this.spritePersonnage.setImage(left6);
                    setNumeroImagePersonnage(7);
                }else if (numeroImagePersonnage==7) {
                    this.spritePersonnage.setImage(left7);
                    setNumeroImagePersonnage(8);
                }else if (numeroImagePersonnage==8) {
                    this.spritePersonnage.setImage(left8);
                    setNumeroImagePersonnage(9);
                }else if (numeroImagePersonnage==9) {
                    this.spritePersonnage.setImage(left9);
                    setNumeroImagePersonnage(1);
                }
            }
            case "RIGHT"->{
                if (numeroImagePersonnage==1) {
                    this.spritePersonnage.setImage(right1);
                    setNumeroImagePersonnage(2);
                }else if (numeroImagePersonnage==2) {
                    this.spritePersonnage.setImage(right2);
                    setNumeroImagePersonnage(3);
                }else if (numeroImagePersonnage==3) {
                    this.spritePersonnage.setImage(right3);
                    setNumeroImagePersonnage(4);
                }else if (numeroImagePersonnage==4) {
                    this.spritePersonnage.setImage(right4);
                    setNumeroImagePersonnage(5);
                }else if (numeroImagePersonnage==5) {
                    this.spritePersonnage.setImage(right5);
                    setNumeroImagePersonnage(6);
                }else if (numeroImagePersonnage==6) {
                    this.spritePersonnage.setImage(right6);
                    setNumeroImagePersonnage(7);
                }else if (numeroImagePersonnage==7) {
                    this.spritePersonnage.setImage(right7);
                    setNumeroImagePersonnage(8);
                }else if (numeroImagePersonnage==8) {
                    this.spritePersonnage.setImage(right8);
                    setNumeroImagePersonnage(9);
                }else if (numeroImagePersonnage==9) {
                    this.spritePersonnage.setImage(right9);
                    setNumeroImagePersonnage(1);
                }
            }
            case "Inactif_UP"->{
                    this.spritePersonnage.setImage(inactifUp1);



            }
            case "Inactif_DOWN"->{
                if (numeroImagePersonnage<=2){
                    this.spritePersonnage.setImage(inactifDown1);
                    setNumeroImagePersonnage(getNumeroImage()+1);
                }else if (numeroImagePersonnage<=4){
                    this.spritePersonnage.setImage(inactifDown2);
                    setNumeroImagePersonnage(getNumeroImage()+1);
                }else if (numeroImagePersonnage<=6) {
                    this.spritePersonnage.setImage(inactifDown3);
                    if (getNumeroImage()<6) {
                        setNumeroImagePersonnage(getNumeroImage() + 1);
                    }else {
                        setNumeroImagePersonnage(1);
                    }
                }
            }
            case "Inactif_LEFT"->{
                if (numeroImagePersonnage<=2){
                    this.spritePersonnage.setImage(inactifLeft1);
                    setNumeroImagePersonnage(getNumeroImage()+1);
                }else if (numeroImagePersonnage<=4){
                    this.spritePersonnage.setImage(inactifLeft2);
                    setNumeroImagePersonnage(getNumeroImage()+1);
                }else if (numeroImagePersonnage<=6) {
                    this.spritePersonnage.setImage(inactifLeft3);
                    if (getNumeroImage()<6) {
                        setNumeroImagePersonnage(getNumeroImage() + 1);
                    }else {
                        setNumeroImagePersonnage(1);
                    }
                }

            }
            case "Inactif_RIGHT"->{
                if (numeroImagePersonnage<=2){
                    this.spritePersonnage.setImage(inactifRight1);
                    setNumeroImagePersonnage(getNumeroImage()+1);
                }else if (numeroImagePersonnage<=4){
                    this.spritePersonnage.setImage(inactifRight2);
                    setNumeroImagePersonnage(getNumeroImage()+1);
                }else if (numeroImagePersonnage<=6) {
                    this.spritePersonnage.setImage(inactifRight3);
                    if (getNumeroImage()<6) {
                        setNumeroImagePersonnage(getNumeroImage() + 1);
                    }else {
                        setNumeroImagePersonnage(1);
                    }
                }
            }
        }
    }

    public int getNumeroImage(){
        return numeroImagePersonnage;
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


}
