package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Personnage;

public class Poison extends Item implements Effet{

    private int ralentissement;
    private int nombreTour;
    public Poison(Environnement environnement, int positionX, int positionY) {
        super(environnement, positionX, positionY, 32, 32);
        this.ralentissement = 4;
        this.nombreTour = 10;

    }

    public Poison(Environnement environnement) {
        super(environnement, 32, 32);
        this.ralentissement = 4;
        this.nombreTour = 10;
    }

    public int getRalentissement() {
        return ralentissement;
    }

    public int getNombreTour() {
        return nombreTour;
    }
    @Override
    public void appliquer(Personnage personnage) {

        personnage.ralentir(getRalentissement(),getNombreTour());
    }
}
