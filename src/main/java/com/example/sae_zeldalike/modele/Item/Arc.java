package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.Vue.VueFlèche;

import java.util.ArrayList;

public class Arc extends Arme{
    private ArrayList<Flèche> flèches;
    private ArrayList<VueFlèche> flèchesEnDéplacement;
    public Arc(int dégâts, int rayonAttaque) {
        super(dégâts, rayonAttaque);
        this.flèches = new ArrayList<>();
        this.flèchesEnDéplacement = new ArrayList<>();
    }

    public ArrayList<Flèche> getFlèches() {
        return this.flèches;
    }

    public ArrayList<VueFlèche> getFlèchesEnDéplacement() {
        return this.flèchesEnDéplacement;
    }
}
