package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Item.Item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VueItem {

    private Item item;
    private ImageView spriteItem;
    private int numeroImageItem;
    private Circle ombre;

    public VueItem(Pane pane, Item item){
        this.item = item;
        this.spriteItem = new ImageView("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin1.png");
        this.numeroImageItem = 1;
        this.spriteItem.setFitWidth(item.getLargeur());
        this.spriteItem.setFitHeight(item.getLongueur());
        this.spriteItem.setId("#"+item.getId());

        spriteItem.setTranslateX(item.getPositionX());
        spriteItem.setTranslateY(item.getPositionY());
        creerOmbre(pane);
        pane.getChildren().add(spriteItem);

        spriteItem.translateXProperty().bind(item.getPositionXProperty());
        spriteItem.translateYProperty().bind(item.getPositionYProperty());


    }

    private void creerOmbre(Pane pane) {
        ombre = new Circle((item.getLargeur()/4));
        ombre.setFill(Color.DARKSLATEGRAY);
        ombre.setId("#"+item.getId());

        pane.getChildren().add(ombre);

        ombre.centerXProperty().bind(spriteItem.translateXProperty().add((item.getLargeur()/2)));
        ombre.centerYProperty().bind(spriteItem.translateYProperty().add(15));
    }


    public void animationItem(){
        switch (numeroImageItem){
            case 1->{
                numeroImageItem++;
                spriteItem.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin"+getNumeroImageItem()+".png"));
            }
            case 2->{
                numeroImageItem++;
                spriteItem.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin"+getNumeroImageItem()+".png"));

            }
            case 3->{
                numeroImageItem++;
                spriteItem.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin"+getNumeroImageItem()+".png"));
            }
            case 4->{
                numeroImageItem++;
                spriteItem.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin"+getNumeroImageItem()+".png"));
            }
            case 5->{
                numeroImageItem++;
                spriteItem.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin"+getNumeroImageItem()+".png"));

            }
            case 6->{
                numeroImageItem++;
                spriteItem.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin"+getNumeroImageItem()+".png"));
            }
            case 7->{
                numeroImageItem++;
                spriteItem.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin"+getNumeroImageItem()+".png"));
            }
            case 8->{
                numeroImageItem++;
                spriteItem.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin"+getNumeroImageItem()+".png"));
            }
            case 9->{
                numeroImageItem=1;
                spriteItem.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin"+getNumeroImageItem()+".png"));
            }
        }
    }

    private int getNumeroImageItem(){
        return numeroImageItem;
    }
}
