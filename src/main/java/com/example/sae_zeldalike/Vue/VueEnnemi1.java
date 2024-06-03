package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Personnage.Ennemi1;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class VueEnnemi1 {

    private Ennemi1 perso;
    private ImageView spritePerso;
    private int numImagePerso;

    public VueEnnemi1 (Pane pane, Ennemi1 ennemi1) {
        this.perso = ennemi1;
        this.numImagePerso=1;
        this.spritePerso = new ImageView("file:src/main/resources/com/example/sae_zeldalike/Personnage/Ennemi/testEnnemi1.png");
        this.spritePerso.setId("#" + ennemi1.getId());
        spritePerso.setFitWidth(ennemi1.getLargeur());
        spritePerso.setFitHeight(ennemi1.getLongueur());
        spritePerso.setTranslateX(ennemi1.getPositionX());
        spritePerso.setTranslateY(ennemi1.getPositionY());
        pane.getChildren().add(spritePerso);
        spritePerso.translateXProperty().bind(ennemi1.getPositionXProperty());
        spritePerso.translateYProperty().bind(ennemi1.getPositionYProperty());
    }

    public void changerImage(){
        if (numImagePerso==1){
            setNumImagePerso(2);
            spritePerso.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Ennemi/testEnnemi1.png"));
        }
        else if (getNumImagePerso()==2){
            setNumImagePerso(1);
            spritePerso.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Ennemi/zombie_spritesheet(2).png"));
        }
    }



    public int getNumImagePerso() {
        return numImagePerso;
    }

    public void setNumImagePerso(int numImagePerso) {
        this.numImagePerso = numImagePerso;
    }

    public Ennemi1 getPerso() {
        return perso;
    }

    public ImageView getSpritePerso() {
        return spritePerso;
    }
}
