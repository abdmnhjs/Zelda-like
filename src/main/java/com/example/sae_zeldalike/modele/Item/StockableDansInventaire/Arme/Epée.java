package com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class Epée extends Arme {


    public Epée(Environnement environnement, int positionX, int positionY) {
        super(environnement, positionX, positionY, 48, 48, 10, 5);
    }

    public Epée(Environnement environnement) {
        super(environnement, 48, 48, 10, 5);
    }




}
