package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Item.Epée;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public class VueEpee {
    private Epée epee;
    private Pane pane;
    private ImageView spriteEpee;

    public VueEpee(Epée epee, Pane pane) {
        this.epee = epee;
        this.pane = pane;

    }

    public void creerFlèche(Pane pane){
        if(this.epee.getDirection().equals("UP")) {
            this.spriteEpee = new ImageView((Element) new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-haut.png"));
        }
        if(this.epee.getDirection().equals("DOWN")) {
            this.spriteEpee = new ImageView((Element) new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-bas.png"));
        }
        if(this.epee.getDirection().equals("RIGHT")) {
            this.spriteEpee = new ImageView((Element) new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-droite.png"));
        }
        if(this.epee.getDirection().equals("LEFT")) {
            this.spriteEpee = new ImageView((Element) new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-gauche.png"));
        }
    }
}
