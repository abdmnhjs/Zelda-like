package com.example.sae_zeldalike.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Link extends Personnage {

    private ObservableList<Piece> pieces;

    public Link(Environnement environnement, int positionX, int positionY) {
        super(100, 0, environnement, positionX, positionY, 5);

        this.pieces= FXCollections.observableArrayList();

    }

    public Link(Environnement environnement) {

        super(100, 0, environnement, 5);
        this.pieces=FXCollections.observableArrayList();
    }


    public ObservableList getPieces(){
        return pieces;
    }

    public void ajouterPiece(Piece piece) {
        pieces.add(piece);
    }

}
