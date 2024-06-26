package com.example.sae_zeldalike.modele.Environnement;

import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private int[][] map;
    private int ligne;
    private int colonne;
    private final int tailleTuile;
    private ArrayList<int[]> coordonnéesToutesTuiles;
    private ArrayList<int[]> coordonnéesTuilesTraversables;
    private ArrayList<int[]> coordonnéesTuilesNonTraversables;

    public Map(String filePath) throws IOException {
        this.map = JsonToMap.loadMapFromJson(filePath);
        this.ligne = map.length;
        this.colonne = map[0].length;
        tailleTuile=32;
        this.coordonnéesToutesTuiles = new ArrayList<>();
        this.coordonnéesTuilesTraversables = new ArrayList<>();
        this.coordonnéesTuilesNonTraversables = new ArrayList<>();

        for(int y = 0 ; y < this.map.length ; y++){
            for(int x = 0 ; x < this.map[y].length ; x++){
                if(this.map[y][x] == 11){
                    this.coordonnéesTuilesTraversables.add(new int[]{y * tailleTuile, x * tailleTuile});
                    this.coordonnéesToutesTuiles.add(new int[]{y * tailleTuile, x * tailleTuile});
                } else {
                    this.coordonnéesTuilesNonTraversables.add(new int[]{y * tailleTuile, x * tailleTuile});
                    this.coordonnéesToutesTuiles.add(new int[]{y * tailleTuile, x * tailleTuile});
                }
            }
        }
    }
    public int[][] getMap() {
        return map;
    }

    public int getTailleTuile(){
        return tailleTuile;
    }

    public int getLigne() {
        return ligne;
    }
    public int getColonne() {
        return colonne;
    }
}
