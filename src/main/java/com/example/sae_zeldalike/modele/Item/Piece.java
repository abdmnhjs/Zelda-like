package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class Piece extends Item {

    private final int valeur;

    public Piece(Environnement environnement, int positionX, int positionY) {
        super(environnement, positionX, positionY);
        this.valeur = 5;
    }
    public Piece(Environnement environnement) {
        super(environnement);
        this.valeur = 5;
    }
}
