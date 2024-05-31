package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.VueItem;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateurItem implements ListChangeListener<Item> {


    private Pane environnement;
    private ArrayList<VueItem> vueItems ;



    public ObservateurItem(Pane jeu, ArrayList<VueItem> vueItems){
        this.environnement=jeu;
        this.vueItems=vueItems;
    }
    @Override
    public void onChanged(Change<? extends Item> change) {

        while (change.next()) {

            if (change.wasAdded()){

                for (Item item : change.getAddedSubList()){
                    VueItem newItem =new VueItem(environnement,item);
                    vueItems.add(newItem);
                }
            }
            else if (change.wasRemoved()){
                for (Item item : change.getRemoved()){
                    this.environnement.getChildren().remove(environnement.lookup("#"+item.getId()));
                    this.environnement.getChildren().remove(environnement.lookup("#O"+item.getId()));
                }
            }
        }
    }
}
