package com.example.sae_zeldalike.modele.Item.NonStockable;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Personnage.Personnage;

import java.util.Random;

public class Poison extends Item implements Effet {

    private int ralentissement;
    private int nombreTour;

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

        Random random = new Random();
        // 50% de chance de ralentir le personnage
        if (random.nextBoolean()) {
            personnage.ralentir(getRalentissement(), getNombreTour());
        }

    }
}
