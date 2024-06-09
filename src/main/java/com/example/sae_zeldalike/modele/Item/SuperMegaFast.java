package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class SuperMegaFast extends Item{

    private int vitesse;

    public SuperMegaFast(Environnement environnement, int positionX, int positionY) {
        super(environnement, positionX, positionY, 32, 32);
        vitesseAleatoires();
    }

    public SuperMegaFast(Environnement environnement) {
        super(environnement, 32, 32);
        vitesseAleatoires();
    }

    private void vitesseAleatoires(){
        int valRandom =(int)((Math.random())*5);

        int signeAleatoire = (int)((Math.random())*2);
        if(signeAleatoire==0){
            vitesse-=valRandom;
            System.out.println(vitesse);
        }else {
            vitesse+=valRandom;
        }
    }

    public int getVitesse() {
        return vitesse;
    }
}
