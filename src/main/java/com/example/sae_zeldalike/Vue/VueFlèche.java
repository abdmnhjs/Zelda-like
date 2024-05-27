package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Item.Flèche;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class VueFlèche {
    private Flèche flèche;
    private Pane pane;

    public VueFlèche(Flèche flèche, Pane pane){
        this.flèche = flèche;
        this.pane = pane;
        creerFlèche(this.pane);
    }

    public void creerFlèche(Pane pane){
        Rectangle spriteFlèche = new Rectangle();
        spriteFlèche.setFill(Color.RED);
        spriteFlèche.translateXProperty().bind(this.flèche.getXProperty());
        spriteFlèche.translateYProperty().bind(this.flèche.getYProperty());
        pane.getChildren().add(spriteFlèche);
    }
}
