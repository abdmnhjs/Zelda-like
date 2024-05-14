package com.example.sae_zeldalike.modele;

public class Ennemi1 extends Personnage {

    public Ennemi1(int pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY) {
        super(pointVie, pointAttaque, environnement, positionX, positionY, 2);
    }

    public Ennemi1(int pointVie, int pointAttaque, Environnement environnement) {
        super(pointVie, pointAttaque, environnement, 2);
    }


}

