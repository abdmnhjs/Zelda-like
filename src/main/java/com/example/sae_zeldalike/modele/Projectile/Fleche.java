package com.example.sae_zeldalike.modele.Projectile;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arc;

public class Fleche extends Projectile {

    private Arc arc;

    public Fleche(Environnement environnement, int positionX, int positionY, int largeur, int longueur, int vitesse,Arc arc) {
        super(environnement, positionX, positionY, largeur, longueur, vitesse);
        this.arc=arc;
    }
}
