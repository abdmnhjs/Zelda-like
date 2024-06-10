package com.example.sae_zeldalike.modele.Projectile;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class BouleDeFeu extends Projectile {

    private int degats;

    public BouleDeFeu(Environnement environnement, int positionX, int positionY, int vitesse) {
        super(environnement, positionX, positionY, 32, 32, vitesse);
        this.degats=10;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }
}
