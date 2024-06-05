package com.example.sae_zeldalike.modele.Item;

import javafx.scene.image.Image;

public interface Stockable {

    Item getItem();

    Image getImage();

    public void utiliserCapacite();
}
