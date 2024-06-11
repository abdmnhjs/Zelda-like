package com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Projectile.Projectile;

import java.util.ArrayList;

public class Arc extends Arme {
    private ArrayList<Projectile> fleches;

    public Arc(Environnement environnement, int positionX, int positionY, int largeur, int longueur, int degats, int rayonAttaque) {
        super(environnement, positionX, positionY, largeur, longueur, degats, rayonAttaque);
        fleches = new ArrayList<>();
    }

    public Arc(Environnement environnement, int largeur, int longueur, int degats, int rayonAttaque) {
        super(environnement, largeur, longueur, degats, rayonAttaque);
        fleches = new ArrayList<>();
    }

    @Override
    public void utiliserCapacite() {

    }


    public void tirerFleche(){
        this.getEnvironnement().getFlèchesEnDéplacement().add(this.fleches.get(0));
        this.fleches.remove(0);
    }

    public ArrayList<Projectile> getFleches() {
        return this.fleches;
    }

}
