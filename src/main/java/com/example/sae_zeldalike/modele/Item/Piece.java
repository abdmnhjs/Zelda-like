package com.example.sae_zeldalike.modele.Item;

public class Piece {

    private static int compteurPiece=0;
    private String id;

    public Piece(){
        this.id="P"+compteurPiece;
        compteurPiece++;
    }

    public String getId() {return id;}
}
