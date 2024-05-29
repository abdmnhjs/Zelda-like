package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Item.Flèche;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueFlèche {
    private Flèche flèche;
    private Pane pane;
    private ImageView spriteFlèche;
    private String sourceImage;

    public VueFlèche(Flèche flèche, Pane pane, String sourceImage){
        this.flèche = flèche;
        this.pane = pane;
        this.sourceImage = sourceImage;
    }

    public void creerFlèche(Pane pane){
        this.spriteFlèche = new ImageView(new Image(this.sourceImage));
        this.spriteFlèche.setFitHeight(this.flèche.getLongueur());
        this.spriteFlèche.setFitWidth(this.flèche.getLargeur());
        this.spriteFlèche.translateXProperty().bind(this.flèche.getXProperty());
        this.spriteFlèche.translateYProperty().bind(this.flèche.getYProperty());
        pane.getChildren().add(this.spriteFlèche);
    }

    public void supprimerFlèche(Pane pane){
        if (this.spriteFlèche != null) {
            pane.getChildren().remove(this.spriteFlèche);
            System.out.println("Flèche view removed from pane");
        } else {
            System.out.println("No spriteFlèche to remove");
        }
    }

    public Flèche getFlèche() {
        return this.flèche;
    }
}
