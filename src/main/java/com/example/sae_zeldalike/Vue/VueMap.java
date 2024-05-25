package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Environnement.Map;
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

        for (int ligne = 0; ligne < map.getMap().length;ligne++){

            for (int colonne = 0;colonne<map.getMap()[ligne].length;colonne++){
                imageView = new ImageView();
                switch (map.getMap()[ligne][colonne]){
                    case 11 -> imageView.setImage(mur);
                    case 54 -> imageView.setImage(sol);
                    case 55 -> imageView.setImage(piece);
                }
                this.tilePane.getChildren().add(imageView);
            }

        }
    }
}
