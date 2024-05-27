package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Personnage.Personnage;

public class Arme {
    private String id;
    static int compteurArme = 0;
    private int dégâts;
    private int rayonAttaque;

    public Arme(int dégâts, int rayonAttaque) {
        this.id = ""+compteurArme;
        this.dégâts = dégâts;
        this.rayonAttaque = rayonAttaque;
        compteurArme++;
    }

    public void faireDégâts(Personnage personnage, int dégâts){
        if(dégâts <= personnage.getPointVie() && dégâts > 0){
            //personnage.setPointDeVie(dégâts);
        }
    }
}
