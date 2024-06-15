package com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Projectile.Fleche;

public class Arc extends Arme {

    public Arc(Environnement environnement) {
        super(environnement, 32, 32, 10, 200);

    }

    @Override
    public void utiliserCapacite() {
        getEnvironnement().ajouterProjectiles(new Fleche(getEnvironnement(),(getPositionX()),(getPositionY()),32,32,getPersonnage().getVitesseDeplacement(),this,this.getDirection()));
    }



}
