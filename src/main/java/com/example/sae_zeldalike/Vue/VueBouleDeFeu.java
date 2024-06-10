package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueBouleDeFeu {
    private Projectile bouleDeFeu;
    private Pane pane;
    private ImageView spriteBouleDeFeu;

    public VueBouleDeFeu(Projectile bouleDeFeu, Pane pane){
        this.bouleDeFeu = bouleDeFeu;
        this.pane = pane;
        this.spriteBouleDeFeu = new ImageView();
        creerBouleDeFeu(this.pane);
    }

    public void creerBouleDeFeu(Pane pane){
        if(this.bouleDeFeu.getDirection().equals("UP")){
            this.spriteBouleDeFeu = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/BouleDeFeu/bouledefeu-haut.png"));
            this.spriteBouleDeFeu.setId(this.bouleDeFeu.getId());
        }
        if(this.bouleDeFeu.getDirection().equals("DOWN")){
            this.spriteBouleDeFeu = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/BouleDeFeu/bouledefeu-bas.png"));
            this.spriteBouleDeFeu.setId(this.bouleDeFeu.getId());
        }
        if(this.bouleDeFeu.getDirection().equals("RIGHT")){
            this.spriteBouleDeFeu = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/BouleDeFeu/bouledefeu-droite.png"));
            this.spriteBouleDeFeu.setId(this.bouleDeFeu.getId());
        }
        if(this.bouleDeFeu.getDirection().equals("LEFT")){
            this.spriteBouleDeFeu = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/BouleDeFeu/bouledefeu-gauche.png"));
            this.spriteBouleDeFeu.setId(this.bouleDeFeu.getId());
        }
        this.spriteBouleDeFeu.setFitHeight(this.bouleDeFeu.getLongueur());
        this.spriteBouleDeFeu.setFitWidth(this.bouleDeFeu.getLargeur());
        this.spriteBouleDeFeu.translateXProperty().bind(this.bouleDeFeu.getXProperty());
        this.spriteBouleDeFeu.translateYProperty().bind(this.bouleDeFeu.getYProperty());
        pane.getChildren().add(this.spriteBouleDeFeu);
    }

    public void supprimerFlèche(Pane pane){
        if (this.spriteBouleDeFeu != null) {
            pane.getChildren().remove(this.spriteBouleDeFeu);
            System.out.println("Flèche view removed from pane");
        } else {
            System.out.println("No spriteFlèche to remove");
        }
    }

    public Projectile getBouleDeFeu() {
        return this.bouleDeFeu;
    }
}
