package com.example.sae_zeldalike.Vue.Item;

import com.example.sae_zeldalike.modele.Item.Item;

import com.example.sae_zeldalike.modele.Item.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

    }

    protected abstract void creerOmbre(Pane pane);

    public ImageView getSpriteItem() {
        return spriteItem;
    }

    public void setSpriteItem(Image image) {
        this.spriteItem.setImage(image);
    }

    public int getNumeroItem(){
        return numeroImageItem;
    }
    public void setNumeroImageItem(int numeroImageItem){
        this.numeroImageItem = numeroImageItem;
    }

    public abstract void animationItem();


}
