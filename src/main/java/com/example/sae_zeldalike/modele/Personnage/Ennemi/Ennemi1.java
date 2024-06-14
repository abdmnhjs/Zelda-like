package com.example.sae_zeldalike.modele.Personnage.Ennemi;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class Ennemi1 extends Ennemi {

    public Ennemi1(Environnement environnement, int positionX, int positionY) {
        super(100, 20, environnement, positionX, positionY, 2, 32,32);
        this.probaAttaque = 45;

    }

    public Ennemi1(Environnement environnement) {
        super(100, 20, environnement,  2, 32,32);
        this.probaAttaque = 45;
    }




}
