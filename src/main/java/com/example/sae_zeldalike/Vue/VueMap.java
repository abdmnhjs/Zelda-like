package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class VueMap {

    private ImageView imageView;
    private Image mur;
    private Image sol;
    private Image piece;
    private TilePane tilePane;
    private Map map;

    public VueMap(TilePane tilePane, Map map) {

        this.tilePane=tilePane;
        this.map=map;
        this.mur= new Image("/home/edetommaso/Documents/S2/SAE Dev/Zelda-like/src/main/resources/54.png");
        this.sol = new Image("/home/edetommaso/Documents/S2/SAE Dev/Zelda-like/src/main/resources/11.png");
        this.piece = new Image("/home/edetommaso/Documents/S2/SAE Dev/Zelda-like/src/main/resources/55.png");

        genererMap();
    }


    public void genererMap(){
        for (int i = 0; i < map.getMap().length;i++){
            imageView = new ImageView();
            switch (map.getMap()[i]){
                case 11 -> imageView.setImage(mur);
                case 54 -> imageView.setImage(sol);
                case 55 -> imageView.setImage(piece);
            }
            this.tilePane.getChildren().add(imageView);
        }
    }
}
