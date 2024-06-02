package com.example.sae_zeldalike.Vue.Item;

import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VuePiece extends VueItem{

    private Image pieceImage1, pieceImage2, pieceImage3, pieceImage4, pieceImage5, pieceImage6, pieceImage7, pieceImage8, pieceImage9;

    public VuePiece(Pane pane, Item item) {
        super(pane, item);
        initialiserImage();

        spriteItem.setImage(pieceImage1);

        creerOmbre(pane);
        pane.getChildren().add(spriteItem);

        //Binding des coordonnées de l'item
        spriteItem.translateXProperty().bind(item.getPositionXProperty());
        spriteItem.translateYProperty().bind(item.getPositionYProperty());

    }
    protected void creerOmbre(Pane pane) {
        ombre = new Circle((item.getLargeur()/4));
        ombre.setFill(Color.DARKSLATEGRAY);
        ombre.setId("O"+item.getId());
        pane.getChildren().add(ombre);
        ombre.centerXProperty().bind(item.getPositionXProperty().add((item.getLargeur()/2)));
        ombre.centerYProperty().bind(item.getPositionYProperty().add(15));
    }

    private void initialiserImage(){

        pieceImage1= new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin1.png");
        pieceImage2= new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin2.png");
        pieceImage3= new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin3.png");
        pieceImage4= new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin4.png");
        pieceImage5= new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin5.png");
        pieceImage6= new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin6.png");
        pieceImage7= new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin7.png");
        pieceImage8= new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin8.png");
        pieceImage9= new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Piece/goldCoin9.png");
    }



    public void animationItem(){
        switch (getNumeroItem()){
            case 1->{

                spriteItem.setImage(pieceImage1);
                setNumeroImageItem(getNumeroItem()+1);
            }
            case 2->{
                spriteItem.setImage(pieceImage2);
                setNumeroImageItem(getNumeroItem()+1);


            }
            case 3->{
                spriteItem.setImage(pieceImage3);
                setNumeroImageItem(getNumeroItem()+1);

            }
            case 4->{
                spriteItem.setImage(pieceImage4);
                setNumeroImageItem(getNumeroItem()+1);

            }
            case 5->{
                spriteItem.setImage(pieceImage5);
                setNumeroImageItem(getNumeroItem()+1);


            }
            case 6->{
                spriteItem.setImage(pieceImage6);
                setNumeroImageItem(getNumeroItem()+1);

            }
            case 7->{
                spriteItem.setImage(pieceImage7);
                setNumeroImageItem(getNumeroItem()+1);
            }
            case 8->{
                spriteItem.setImage(pieceImage8);
                setNumeroImageItem(getNumeroItem()+1);
            }
            case 9->{
                spriteItem.setImage(pieceImage9);
                setNumeroImageItem(1);
            }
        }
    }

}
