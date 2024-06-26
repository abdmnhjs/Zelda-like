package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi1;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueEnnemi1 {

    private Ennemi1 perso;
    private ImageView spritePerso;
    private int numImagePerso;

    public VueEnnemi1 (Pane pane, Ennemi1 ennemi1) {
        this.perso = ennemi1;
        this.numImagePerso=1;
        this.spritePerso = new ImageView("file:src/main/resources/com/example/sae_zeldalike/Personnage/Ennemi/testEnnemi1.png");
        this.spritePerso.setId("#" + ennemi1.getId());
        spritePerso.setFitWidth(32);
        spritePerso.setFitHeight(32);
        spritePerso.setTranslateX(ennemi1.getPositionX());
        spritePerso.setTranslateY(ennemi1.getPositionY());
        pane.getChildren().add(spritePerso);
        spritePerso.translateXProperty().bind(ennemi1.getPositionXProperty());
        spritePerso.translateYProperty().bind(ennemi1.getPositionYProperty());
    }
}
