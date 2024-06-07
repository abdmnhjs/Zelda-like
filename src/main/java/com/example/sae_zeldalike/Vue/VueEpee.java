package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Item.Epée;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javax.swing.text.Element;


public class VueEpee {
    private Epée epee;
    private Pane pane;
    private Personnage personnage;
    private ImageView spriteEpee;

    public VueEpee(Epée epee, Pane pane, Personnage personnage) {
        this.epee = epee;
        this.pane = pane;
        this.personnage = personnage;
        creerEpee(this.pane);
    }

    public void creerEpee(Pane pane){
        if(this.epee.getDirection().equals("UP")) {
            this.supprimerEpee(this.pane);
            this.spriteEpee = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-haut.png"));
            this.spriteEpee.translateXProperty().bind(this.personnage.getPositionXProperty().add(16));
            this.spriteEpee.translateYProperty().bind(this.personnage.getPositionYProperty());
        }
        if(this.epee.getDirection().equals("DOWN")) {
            this.supprimerEpee(this.pane);
            this.spriteEpee = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-bas.png"));
            this.spriteEpee.translateXProperty().bind(this.personnage.getPositionXProperty().add(16));
            this.spriteEpee.translateYProperty().bind(this.personnage.getPositionYProperty().add(32));
        }
        if(this.epee.getDirection().equals("RIGHT")) {
            this.supprimerEpee(this.pane);
            this.spriteEpee = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-droite.png"));
            this.spriteEpee.translateXProperty().bind(this.personnage.getPositionXProperty().add(32));
            this.spriteEpee.translateYProperty().bind(this.personnage.getPositionYProperty().add(16));
        }
        if(this.epee.getDirection().equals("LEFT")) {
            this.supprimerEpee(this.pane);
            this.spriteEpee = new ImageView(new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-gauche.png"));
            this.spriteEpee.translateXProperty().bind(this.personnage.getPositionXProperty());
            this.spriteEpee.translateYProperty().bind(this.personnage.getPositionYProperty().add(16));
        }
        this.spriteEpee.setFitHeight(this.epee.getLongueur());
        this.spriteEpee.setFitWidth(this.epee.getLargeur());
        pane.getChildren().add(this.spriteEpee);
    }

    public void supprimerEpee(Pane pane){
        if (this.spriteEpee != null) {
            pane.getChildren().remove(this.spriteEpee);
        }
    }
}
