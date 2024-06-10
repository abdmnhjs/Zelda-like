package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueFlèche {
    private Projectile projectile;
    private Pane pane;
    private ImageView spriteFlèche;

    public VueFlèche(Projectile projectile, Pane pane){
        this.projectile = projectile;
        this.pane = pane;
        this.spriteFlèche = new ImageView();
        creerFlèche(this.pane);
    }

    public void creerFlèche(Pane pane){
        if(this.projectile.getDirection().equals("UP")){
            this.spriteFlèche = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-haut.png"));
            this.spriteFlèche.setId(this.projectile.getId());
        }
        if(this.projectile.getDirection().equals("DOWN")){
            this.spriteFlèche = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-bas.png"));
            this.spriteFlèche.setId(this.projectile.getId());
        }
        if(this.projectile.getDirection().equals("RIGHT")){
            this.spriteFlèche = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-droite.png"));
            this.spriteFlèche.setId(this.projectile.getId());
        }
        if(this.projectile.getDirection().equals("LEFT")){
            this.spriteFlèche = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Flèche/flèche-gauche.png"));
            this.spriteFlèche.setId(this.projectile.getId());
        }
        this.spriteFlèche.setFitHeight(this.projectile.getLongueur());
        this.spriteFlèche.setFitWidth(this.projectile.getLargeur());
        this.spriteFlèche.translateXProperty().bind(this.projectile.getPositionXProperty());
        this.spriteFlèche.translateYProperty().bind(this.projectile.getPositionYProperty());
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

    public Projectile getFlèche() {
        return this.projectile;
    }
}
