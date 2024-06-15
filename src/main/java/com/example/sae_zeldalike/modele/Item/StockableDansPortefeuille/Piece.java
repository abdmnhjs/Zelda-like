package com.example.sae_zeldalike.modele.Item.StockableDansPortefeuille;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Item;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Piece extends Item {

    private final IntegerProperty valeur;

    public Piece(Environnement environnement) {
        super(environnement,16,16);
        this.valeur = new SimpleIntegerProperty(5);
    }

    public int getValeur() {
        return valeur.getValue();
    }

}
