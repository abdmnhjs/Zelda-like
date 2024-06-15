package com.example.sae_zeldalike.modele.Item.StockableDansInventaire;

import com.example.sae_zeldalike.modele.Item.Item;

public interface Stockable {

    Item getItem();

    boolean effetUtiliser();

    void utiliserCapacite();
}
