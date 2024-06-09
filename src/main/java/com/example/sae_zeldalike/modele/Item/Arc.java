package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

import java.util.ArrayList;

public class Arc extends Arme{
    private ArrayList<Projectile> fleches;
    public Arc(int dégâts, int rayonAttaque, Environnement environnement) {
        super(dégâts, rayonAttaque, environnement);
        this.fleches = new ArrayList<>();
    }

    public void tirerFleche(){
        this.environnement.getFlèchesEnDéplacement().add(this.fleches.get(0));
        this.fleches.remove(0);
    }

    public ArrayList<Projectile> getFleches() {
        return this.fleches;
    }

}
