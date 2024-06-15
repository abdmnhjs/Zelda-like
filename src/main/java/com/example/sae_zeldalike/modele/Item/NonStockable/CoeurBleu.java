package com.example.sae_zeldalike.modele.Item.NonStockable;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Item;

public class CoeurBleu extends Item {

    private int vieAdditionelle;

    public CoeurBleu(Environnement environnement) {
        super(environnement, 16, 16);
        vieAleatoires();
    }

    private void vieAleatoires(){
        int vieAleatoire = (int)((Math.random())*2);
        if(vieAleatoire==0){
            vieAdditionelle=5;

        }else{
            vieAdditionelle=10;
        }
    }

    public int getVieAdditionelle() {
        return vieAdditionelle;
    }
}
