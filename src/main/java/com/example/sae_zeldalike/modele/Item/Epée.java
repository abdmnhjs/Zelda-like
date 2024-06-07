package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public class Epée extends Arme{
    private static int compteurEpee = 0;
    private String id;
    public Epée(int x, int y, int degats, int rayonAttaque, Environnement environnement){
        super(degats, rayonAttaque, environnement);
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.direction = "N";
        this.id = "E"+compteurEpee;
        this.longueur = 16;
        this.largeur = 16;
        compteurEpee++;
    }

    @Override
    public int getRayonAttaque() {
        return super.getRayonAttaque();
    }

    public String getId() {
        return this.id;
    }
}
