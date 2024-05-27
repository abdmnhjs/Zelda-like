package com.example.sae_zeldalike.modele.Item;

import java.util.ArrayList;

public class Arc extends Arme{
    private ArrayList<Flèche> flèches;
    public Arc(int dégâts, int rayonAttaque) {
        super(dégâts, rayonAttaque);
        this.flèches = new ArrayList<>();
    }
}
