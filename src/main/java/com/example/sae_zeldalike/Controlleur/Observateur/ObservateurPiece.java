package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.modele.Item.Piece;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurPiece implements ListChangeListener<Piece> {


    private Pane environnement;
    @Override
    public void onChanged(Change<? extends Piece> change) {

        while (change.next()) {
            if (change.wasAdded()){
                for (Piece piece : change.getAddedSubList()){

                }
            }
            else if (change.wasRemoved()){
                for (Piece piece : change.getRemoved()){

                    this.environnement.getChildren().remove(environnement.lookup("#"+piece.getId()));
                }
            }
        }
    }
}
