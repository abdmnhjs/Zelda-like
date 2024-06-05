package com.example.sae_zeldalike.modele.Item;

import javafx.beans.property.SimpleIntegerProperty;

public class Epée extends Arme{
    private String id;
    private static int compteurEpee = 0;
    public Epée(int x, int y, int degats, int rayonAttaque){
        super(degats, rayonAttaque);
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.id = "E"+compteurEpee;
        compteurEpee++;
    }
}
