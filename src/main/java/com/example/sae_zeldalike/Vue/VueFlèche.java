package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Item.Flèche;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueFlèche {
    private Flèche flèche;
    private Pane pane;
    private ImageView spriteFlèche;

    public VueFlèche(Flèche flèche, Pane pane){
        this.flèche = flèche;
        this.pane = pane;
    }

    public void creerFlèche(Pane pane){
        if(this.flèche.getDirection().equals("UP")){
            this.spriteFlèche = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-haut.png"));
        }
        if(this.flèche.getDirection().equals("DOWN")){
            this.spriteFlèche = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-bas.png"));
        }
        if(this.flèche.getDirection().equals("RIGHT")){
            this.spriteFlèche = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-droite.png"));
        }
        if(this.flèche.getDirection().equals("LEFT")){
            this.spriteFlèche = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-gauche.png"));
        }
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
