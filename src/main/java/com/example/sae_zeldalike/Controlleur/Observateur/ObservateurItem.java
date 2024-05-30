package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.VueItem;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurItem implements ListChangeListener<Item> {


    private Pane environnement;

    public ObservateurItem(Pane jeu){
        this.environnement=jeu;
    }
    @Override
    public void onChanged(Change<? extends Item> change) {

        while (change.next()) {

            if (change.wasAdded()){

                for (Item item : change.getAddedSubList()){
                    new VueItem(environnement,item);
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
