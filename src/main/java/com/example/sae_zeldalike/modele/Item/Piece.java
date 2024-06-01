package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Piece extends Item {

    private final IntegerProperty valeur;

    public Piece(Environnement environnement, int positionX, int positionY) {
        super(environnement, positionX, positionY,16,16);
        this.valeur = new SimpleIntegerProperty(5);
    }

    public Piece(Environnement environnement) {
        super(environnement,16,16);
        this.valeur = new SimpleIntegerProperty(5);
    }

    public int getValeur() {
        return valeur.getValue();
    }

    public IntegerProperty valeurProperty() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur.setValue(valeur);
    }
}
