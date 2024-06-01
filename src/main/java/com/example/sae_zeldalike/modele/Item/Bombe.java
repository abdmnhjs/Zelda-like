package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class Bombe extends Item implements Stockable {

    private int degat;
    private int rayonAttaque;

    public Bombe(Environnement environnement, int positionX, int positionY) {
        super(environnement, positionX, positionY,48,48);
        this.degat = 0;
        this.rayonAttaque = getLargeur()/2;
    }

    public Bombe(Environnement environnement) {
        super(environnement,48,48);
        this.degat = 0;
        this.rayonAttaque = getLargeur()/2;
    }

    public int getRayonAttaque() {
        return rayonAttaque;
    }

    public void setRayonAttaque(int rayonAttaque) {
        this.rayonAttaque = rayonAttaque;
    }

    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    @Override
    public Item getItem() {
        return this;
    }

}
