package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class CoeurBleu extends Item{

    private int vieAdditionelle;

    public CoeurBleu(Environnement environnement, int positionX, int positionY) {
        super(environnement, positionX, positionY, 16, 16);
        this.vieAdditionelle = 10;
    }

    public CoeurBleu(Environnement environnement) {
        super(environnement, 16, 16);
        this.vieAdditionelle = 10;
    }

    public int getVieAdditionelle() {
        return vieAdditionelle;
    }
}