package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class CoeurRouge extends Item{

    private int pointDeVie;

    public CoeurRouge(Environnement environnement, int positionX, int positionY, int pv) {
        super(environnement, positionX, positionY, 16, 16);
        if (pv>1 && pv<=10){
            this.pointDeVie=pv;
        }

    }

    public CoeurRouge(Environnement environnement,int pv) {
        super(environnement, 16, 16);
        if (pv>1 && pv<=10){
            this.pointDeVie=pv;
        }
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

}
