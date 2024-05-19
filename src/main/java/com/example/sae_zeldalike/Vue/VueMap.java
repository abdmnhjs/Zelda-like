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
        this.mur= new Image("file:src/main/resources/com/example/sae_zeldalike/Map/54.png");
        this.sol = new Image("file:src/main/resources/com/example/sae_zeldalike/Map/11.png");
        this.piece = new Image("file:src/main/resources/com/example/sae_zeldalike/Map/55.png");


        genererMap();
    }


    public void genererMap(){
        for (int i = 0; i < map.getMap().length;i++){
            imageView = new ImageView();
            imageView.setId("map1");
            switch (map.getMap()[i]){
                case 11 -> imageView.setImage(mur);
                case 54 -> imageView.setImage(sol);
                case 55 -> imageView.setImage(piece);
            }
            this.tilePane.getChildren().add(imageView);
        }
    }

    public ImageView getImageView(){
        return this.imageView;
    }

    public Image getMur() {
        return this.mur;
    }

    public Image getSol() {
        return this.sol;
    }

    public TilePane getTilePane() {
        return this.tilePane;
    }
}
