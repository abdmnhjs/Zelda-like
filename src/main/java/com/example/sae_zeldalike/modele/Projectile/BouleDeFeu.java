package com.example.sae_zeldalike.modele.Projectile;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class BouleDeFeu extends Projectile {


    public BouleDeFeu(Environnement environnement, int positionX, int positionY) {
        super(environnement, positionX, positionY, 32, 32, 20);
    }
}
