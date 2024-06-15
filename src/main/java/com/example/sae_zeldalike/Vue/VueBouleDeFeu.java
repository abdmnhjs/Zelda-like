package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.Vue.Projectile.VueProjectile;
import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class VueBouleDeFeu extends VueProjectile {

    private Image bdfH,bdfD,bdfB,bdfG;

    public VueBouleDeFeu( Pane pane,Projectile projectile){
        super(pane,projectile);
        initialiserProjectile();
        spriteProjectile.setImage(null);


        pane.getChildren().add(spriteProjectile);
//        creerBouleDeFeu(this.pane);
    }

    @Override
    public void animationProjectile() {
        switch (projectile.getDirection()){
            case "UP"->{
                spriteProjectile.setImage(bdfH);
            }
            case "DOWN"->{
                spriteProjectile.setImage(bdfB);
            }
            case "LEFT"->{
                spriteProjectile.setImage(bdfG);
            }case "RIGHT"->{
                spriteProjectile.setImage(bdfD);
            }
        }
    }

    @Override
    protected void initialiserProjectile() {

        bdfH = new Image("file:src/main/resources/com/example/sae_zeldalike/BouleDeFeu/bouledefeu-haut.png");
        bdfD = new Image("file:src/main/resources/com/example/sae_zeldalike/BouleDeFeu/bouledefeu-droite.png");
        bdfB= new Image("file:src/main/resources/com/example/sae_zeldalike/BouleDeFeu/bouledefeu-bas.png");
        bdfG = new Image("file:src/main/resources/com/example/sae_zeldalike/BouleDeFeu/bouledefeu-gauche.png");
    }

    @Override
    public Image getImagePrincipale() {
        return bdfH;
    }



}
