package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.modele.Environnement.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class VueMap {

    private ImageView imageView;
    private Image mur;
    private ImageView murView;
    private Image sol;
    private Image piece;
    private TilePane tilePane;
    private Map map;
    private ArrayList<ImageView> obstacles;

    public VueMap(TilePane tilePane, Map map) {

        this.tilePane=tilePane;
        this.map=map;
        this.mur= new Image("file:src/main/resources/com/example/sae_zeldalike/Map/54.png");
        this.sol = new Image("file:src/main/resources/com/example/sae_zeldalike/Map/11.png");
        this.piece = new Image("file:src/main/resources/com/example/sae_zeldalike/Map/55.png");
        this.obstacles = new ArrayList<>();

        genererMap();
    }

    public void genererMap(){
        for (int i = 0; i < map.getMap().length;i++){

            switch (map.getMap()[i]){
                case 54 -> {
                    ImageView obstacle = new ImageView(mur);
                    this.tilePane.getChildren().add(obstacle);
                    this.obstacles.add(obstacle);
                }
                case 11 -> this.tilePane.getChildren().add(new ImageView(sol));
                case 55 -> this.tilePane.getChildren().add(new ImageView(piece));
            }

        }
    }

    public TilePane getTilePane() {
        return this.tilePane;
    }

    public Image getMur() {
        return this.mur;
    }

    public ImageView getMurView() {
        return this.murView;
    }

    public Image getSol() {
        return this.sol;
    }

    public Image getPiece() {
        return this.piece;
    }

    public ArrayList<ImageView> getObstacles() {
        return this.obstacles;
    }
}