package com.example.sae_zeldalike.modele.Environnement;

import java.util.ArrayList;

public class Map {

    private int[][] map;
    private int ligne;
    private int colonne;
    private ArrayList<int[]> coordonnéesToutesTuiles;
    private ArrayList<int[]> coordonnéesTuilesTraversables;
    private ArrayList<int[]> coordonnéesTuilesNonTraversables;

    public Map(){
        this.map= new int[][]
                {       {54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54},
                        {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 54},
                        {11, 11, 11, 11, 11, 11, 11, 11, 55, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 55, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 55, 11, 11, 54},
                        {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 54},
                        {54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 11, 11, 11, 11, 11, 54},
                        {54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 11, 11, 11, 11, 11, 54},
                        {54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 11, 11, 11, 11, 11, 54},
                        {54, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 55, 11, 11, 54},
                        {54, 11, 11, 55, 11, 11, 11, 11, 11, 55, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 55, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 54},
                        {54, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 54},
                        {54, 11, 11, 11, 11, 11, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54},
                        {54, 11, 11, 11, 11, 11, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54},
                        {54, 11, 11, 55, 11, 11, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54},
                        {54, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 55, 11, 11, 11, 11, 11, 11, 11, 11, 11, 55, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 55, 54},
                        {54, 11, 11, 11, 11, 11, 55, 11, 11, 11, 11, 11, 11, 11, 11, 11, 55, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 55, 11, 11, 11, 11, 54},
                        {54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54},
                        {54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54},
                        {54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54}};


        this.ligne=17;
        this.colonne=34;

        this.coordonnéesToutesTuiles = new ArrayList<>();
        this.coordonnéesTuilesTraversables = new ArrayList<>();
        this.coordonnéesTuilesNonTraversables = new ArrayList<>();

        for(int y = 0 ; y < this.map.length ; y++){
            for(int x = 0 ; x < this.map[y].length ; x++){
                if(this.map[y][x] != 54){
                    this.coordonnéesTuilesTraversables.add(new int[]{y * 32, x * 32});
                    this.coordonnéesToutesTuiles.add(new int[]{y * 32, x * 32});
                } else {
                    this.coordonnéesTuilesNonTraversables.add(new int[]{y * 32, x * 32});
                    this.coordonnéesToutesTuiles.add(new int[]{y * 32, x * 32});
                }
            }
        }
    }


    public int[][] getMap() {
        return map;
    }

    public ArrayList<int[]> getCoordonnéesToutesTuiles() {
        return this.coordonnéesToutesTuiles;
    }

    public ArrayList<int[]> getCoordonnéesTuilesTraversables() {
        return this.coordonnéesTuilesTraversables;
    }

    public ArrayList<int[]> getCoordonnéesTuilesNonTraversables() {
        return this.coordonnéesTuilesNonTraversables;
    }

    public int getLigne() {
        return ligne;
    }
    public int getColonne() {
        return colonne;
    }
}
