package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

import java.util.ArrayList;

public class Ennemi1 extends Ennemi {

    public Ennemi1(Environnement environnement, int positionX, int positionY) {
        super(100, 20, environnement, positionX, positionY, 5, 32,32);
        this.probaAttaque = 45;

    }

    public Ennemi1(Environnement environnement) {
        super(100, 20, environnement,  5, 32,32);
        this.probaAttaque = 45;
    }
}
