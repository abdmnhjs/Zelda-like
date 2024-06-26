package com.example.sae_zeldalike.modele.Item.NonStockable;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Item;

public class SuperMegaFast extends Item {

    private int vitesse;

    public SuperMegaFast(Environnement environnement) {
        super(environnement, 32, 32);
        vitesseAleatoires();
    }

    private void vitesseAleatoires(){
        int valRandom =(int)((Math.random())*5);
        int signeAleatoire = (int)((Math.random())*2);
        if(signeAleatoire==0){
            vitesse-=valRandom;
        }else {
            vitesse+=valRandom;
        }
    }

    public int getVitesse() {
        return vitesse;
    }
}
