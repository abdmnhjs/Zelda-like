package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurItem implements ListChangeListener<Item> {


    private Pane environnement;
    @Override
    public void onChanged(Change<? extends Item> change) {

        while (change.next()) {
            if (change.wasAdded()){
                for (Item item : change.getAddedSubList()){

                }
            }
            else if (change.wasRemoved()){
                for (Item item : change.getRemoved()){

                    this.environnement.getChildren().remove(environnement.lookup("#"+item.getId()));
                }
            }
        }
    }
}
