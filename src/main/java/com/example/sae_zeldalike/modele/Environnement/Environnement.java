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

    public boolean estDevantObstacle(int x, int y) {
        for (int i = 0; i < this.map.getCoordonnéesTuilesNonTraversables().size(); i++) {
            if (x < this.map.getCoordonnéesTuilesNonTraversables().get(i)[1] + 32 &&
                    x + 32 > this.map.getCoordonnéesTuilesNonTraversables().get(i)[1] &&
                    y < this.map.getCoordonnéesTuilesNonTraversables().get(i)[0] + 32 &&
                    y + 32 > this.map.getCoordonnéesTuilesNonTraversables().get(i)[0]) {
                return true;
            }
        }
        return false;
    }



    public String getId() {
        return id;
    }


}


