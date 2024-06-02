package com.example.sae_zeldalike.Vue.Personnage;

import com.example.sae_zeldalike.modele.Personnage.Ennemi1;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VueEnnemi1 extends VuePersonnage{


    private Image spritePerso1,spritePerso2;


    public VueEnnemi1 (Pane pane, Personnage personnage) {
        super(pane, personnage);
        initialiser();
        this.spritePersonnage.setImage(spritePerso1);

        creerOmbre(pane);
        pane.getChildren().add(spritePersonnage);
        spritePersonnage.translateXProperty().bind(personnage.getPositionXProperty());
        spritePersonnage.translateYProperty().bind(personnage.getPositionYProperty());
    }

    private void initialiser(){
        spritePerso1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Ennemi/1.png");
        spritePerso2 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Ennemi/2.png");
    }

    @Override
    protected void creerOmbre(Pane pane) {
        ombre = new Circle((personnage.getLargeur()/2.5));
        ombre.setFill(Color.RED);
        ombre.setId("O"+personnage.getId());

        pane.getChildren().add(this.ombre);

        ombre.centerXProperty().bind(spritePersonnage.translateXProperty().add(personnage.getLargeur()/2));
        ombre.centerYProperty().bind(spritePersonnage.translateYProperty().add(personnage.getLongueur()-5));
    }

    public void animation(){
        if (numeroImagePersonnage==1){
            setNumeroImagePersonnage(2);
            spritePersonnage.setImage(spritePerso1);
        }
        else if (numeroImagePersonnage==2){
            setNumeroImagePersonnage(1);
            spritePersonnage.setImage(spritePerso2);
        }
    }


}
