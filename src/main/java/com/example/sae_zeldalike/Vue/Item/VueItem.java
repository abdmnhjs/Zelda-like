package com.example.sae_zeldalike.Vue.Item;

import com.example.sae_zeldalike.modele.Item.Item;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public abstract class VueItem {

    protected Item item;
    protected ImageView spriteItem;
    private int numeroImageItem;
    protected Circle ombre;

    public VueItem(Pane pane, Item item){
        this.item = item;
        this.numeroImageItem=1;
        this.spriteItem=new ImageView();
        spriteItem.setFitWidth(item.getLargeur());
        spriteItem.setFitHeight(item.getLongueur());
        spriteItem.setId(item.getId());
        spriteItem.setTranslateX(item.getPositionX());
        spriteItem.setTranslateY(item.getPositionY());

        //Binding des coordonnées de l'item
        spriteItem.translateXProperty().bind(item.getPositionXProperty());
        spriteItem.translateYProperty().bind(item.getPositionYProperty());

    }

    protected abstract void creerOmbre(Pane pane);
    protected abstract void initialiserItem();
    public int getNumeroItem(){
        return numeroImageItem;
    }
    public void setNumeroImageItem(int numeroImageItem){
        this.numeroImageItem = numeroImageItem;
    }

    public abstract void animationItem();

    public abstract Image getImagePrincipale();

     public String getSpriteId(){
         return spriteItem.getId();
     }


}
