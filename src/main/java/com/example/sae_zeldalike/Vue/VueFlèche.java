package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Item.Flèche;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class VueFlèche {
    private Flèche flèche;
    private Pane pane;
    private ImageView spriteFlèche;

    public VueFlèche(Flèche flèche, Pane pane){
        this.flèche = flèche;
        this.pane = pane;
        creerFlèche(this.pane);
    }

    public void creerFlèche(Pane pane){
        this.spriteFlèche = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Flèche/flècheARemplacerPlusTard.png"));
        this.spriteFlèche.setFitHeight(this.flèche.getLongueur());
        this.spriteFlèche.setFitWidth(this.flèche.getLargeur());
        this.spriteFlèche.translateXProperty().bind(this.flèche.getXProperty());
        this.spriteFlèche.translateYProperty().bind(this.flèche.getYProperty());
        pane.getChildren().add(this.spriteFlèche);
    }

    public void supprimerFlèche(Pane pane){
        if(pane.getChildren().contains(this.spriteFlèche)){
            pane.getChildren().remove(this.spriteFlèche);
        }
    }
}
