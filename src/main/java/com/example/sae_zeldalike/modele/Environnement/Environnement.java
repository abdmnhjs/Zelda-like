package com.example.sae_zeldalike.modele.Environnement;

import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private String id;
    private static int compteurEnvironnement=0;
    private ObservableList<Personnage> personnages;
    private Map map;

    public Environnement(Map map){

        this.id = "E"+compteurEnvironnement;
        compteurEnvironnement++;
        this.map=map;
        this.personnages= FXCollections.observableArrayList();
    }

    public void ajouterPersonnage(Personnage personnage){
        personnages.add(personnage);
    }

    public ObservableList<Personnage> getPersonnages() {
        return personnages;
    }

    public Map getMap() {
        return map;
    }

    public boolean estDansLimiteTerrain(int x,int y){
        return ((x>0&&x<((map.getColonne()-1)*32))&&(y>0 && y<((map.getLigne()-1)*32)));

    }

    public boolean estDevantObstacle(int x, int y){
        if (estDansLimiteTerrain(x,y)) {
            int newX = x - 32;
            int newY = y - 32;
            if (this.map.getMap()[(newY * map.getLigne()) + newX] == 54) {
                return true;
            }
        }
        return false;
    }


    public String getId() {
        return id;
    }


}


