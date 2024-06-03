package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class Ennemi1 extends Ennemi {

    public Ennemi1(Environnement environnement,int positionX, int positionY) {
        super(100, 1, environnement, positionX, positionY, 5, 52,52);
        this.environnement.getPersonnages().add(this);
    }


}
