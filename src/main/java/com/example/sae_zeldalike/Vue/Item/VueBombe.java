package com.example.sae_zeldalike.Vue.Item;

import com.example.sae_zeldalike.modele.Item.Item;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VueBombe extends VueItem {

    private Image bombe1, bombe2, bombe3, bombe4, bombe5, bombe6, bombe7,
            bombe8, bombe9, bombe10, bombe11, bombe12,bombe13,bombe14;

    public VueBombe(Pane pane, Item item) {
        super(pane, item);
        initialiserItem();

        spriteItem.setImage(bombe1);
        creerOmbre(pane);
        pane.getChildren().add(spriteItem);

        //Binding des coordonnées de l'item
        spriteItem.translateXProperty().bind(item.getPositionXProperty());
        spriteItem.translateYProperty().bind(item.getPositionYProperty());
    }

    protected void creerOmbre(Pane pane) {
        ombre = new Circle((item.getLargeur()/8));
        ombre.setFill(Color.TRANSPARENT);
        ombre.setId("O"+item.getId());
        pane.getChildren().add(ombre);
        ombre.centerXProperty().bind(item.getPositionXProperty().add((item.getLargeur()/2)));
        ombre.centerYProperty().bind(item.getPositionYProperty().add((item.getLongueur()/2)));
    }

    private void initialiserItem(){
        bombe1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_1.png");
        bombe2 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_2.png");
        bombe3 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_3.png");
        bombe4 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_4.png");
        bombe5 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_5.png");
        bombe6 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_6.png");
        bombe7 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_7.png");
        bombe8 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_8.png");
        bombe9 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_9.png");
        bombe10 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_10.png");
        bombe11 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_11.png");
        bombe12 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_12.png");
        bombe13 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_13.png");
        bombe14 = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Bombe/Bombe_14.png");


    }

    @Override
    public void animationItem(){
        switch (getNumeroItem()){
            case 1->{

                spriteItem.setImage(bombe1);
                setNumeroImageItem(getNumeroItem()+1);
            }
            case 2->{
                spriteItem.setImage(bombe2);
                setNumeroImageItem(getNumeroItem()+1);


            }
            case 3->{
                spriteItem.setImage(bombe3);
                setNumeroImageItem(getNumeroItem()+1);

            }
            case 4->{
                spriteItem.setImage(bombe4);
                setNumeroImageItem(getNumeroItem()+1);

            }
            case 5->{
                spriteItem.setImage(bombe5);
                setNumeroImageItem(getNumeroItem()+1);

            }
            case 6->{
                spriteItem.setImage(bombe6);
                setNumeroImageItem(getNumeroItem()+1);

            }
            case 7->{
                spriteItem.setImage(bombe7);
                setNumeroImageItem(getNumeroItem()+1);
            }
            case 8->{
                spriteItem.setImage(bombe8);
                setNumeroImageItem(getNumeroItem()+1);
            }
            case 9->{
                spriteItem.setImage(bombe9);
                setNumeroImageItem(10);
            }
            case 10->{
                spriteItem.setImage(bombe10);
                setNumeroImageItem(11);
            }
            case 11->{
                spriteItem.setImage(bombe11);
                setNumeroImageItem(12);

            }
            case 12->{
                spriteItem.setImage(bombe12);
                setNumeroImageItem(13);

            }
            case 13->{
                spriteItem.setImage(bombe13);
                setNumeroImageItem(14);
            }
            case 14->{
                spriteItem.setImage(bombe14);
                setNumeroImageItem(1);
            }
        }
    }
}
