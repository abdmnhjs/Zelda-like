package com.example.sae_zeldalike.Vue.Projectile;

import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class VueProjectile {

    protected Projectile projectile;
    protected ImageView spriteProjectile;


    public VueProjectile(Pane pane, Projectile projectile){
        this.projectile = projectile;

        this.spriteProjectile=new ImageView();
        spriteProjectile.setFitWidth(projectile.getLargeur());
        spriteProjectile.setFitHeight(projectile.getLongueur());
        spriteProjectile.setId(projectile.getId());
        spriteProjectile.setTranslateX(projectile.getPositionX());
        spriteProjectile.setTranslateY(projectile.getPositionY());

        //Binding des coordonnées de l'item
        spriteProjectile.translateXProperty().bind(projectile.getPositionXProperty());
        spriteProjectile.translateYProperty().bind(projectile.getPositionYProperty());

    }
    public abstract void animationProjectile();
    protected abstract void initialiserProjectile();
    public abstract Image getImagePrincipale();

    public String getSpriteId(){
        return spriteProjectile.getId();
    }



    public Projectile getProjectile() {
        return projectile;
    }
}
