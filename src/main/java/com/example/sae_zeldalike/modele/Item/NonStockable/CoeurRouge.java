package com.example.sae_zeldalike.modele.Item.NonStockable;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Item;

public class CoeurRouge extends Item {

    private int pointDeVie;

    public CoeurRouge(Environnement environnement) {
        super(environnement, 16, 16);
        vieAleatoires();
    }

    private void vieAleatoires(){
        int vieAleatoire = (int)((Math.random())*10);
        if(vieAleatoire<=5){
            pointDeVie=vieAleatoire+1;

        }else if (vieAleatoire>5){
            pointDeVie=vieAleatoire+1;
        }
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

}
