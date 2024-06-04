package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Personnage.Personnage;

import java.util.ArrayList;

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
        ArrayList<Personnage> dead = new ArrayList<>();
        if(dégâts <= personnage.getPointVie() && dégâts > 0){
            personnage.setPointDeVie(personnage.getPointVie() - dégâts);
            if (!personnage.estVivant()){
                dead.add(personnage);
            }

        }
        for (Personnage perso : dead) {
            perso.getEnvironnement().supprimerPersonnage(perso);
        }
    }

    public int getDégâts() {
        return this.dégâts;
    }

    public int getRayonAttaque() {
        return this.rayonAttaque;
    }
}
