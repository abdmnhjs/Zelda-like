package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.Item.VueBombe;
import com.example.sae_zeldalike.Vue.Item.VueCoeurRouge;
import com.example.sae_zeldalike.Vue.Item.VueItem;
import com.example.sae_zeldalike.Vue.Item.VuePiece;
import com.example.sae_zeldalike.modele.Item.Bombe;
import com.example.sae_zeldalike.modele.Item.CoeurRouge;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateurItem implements ListChangeListener<Item> {


    private Pane environnement;
    private ArrayList<VueItem> vueItems ;

    private Item itemsupprimer;
    private Item itemAjouter;



    public ObservateurItem(Pane jeu, ArrayList<VueItem> vueItems) {
        this.environnement=jeu;
        this.vueItems=vueItems;

    }
    @Override
    public void onChanged(Change<? extends Item> change) {

        while (change.next()) {

            if (change.wasAdded()){


                for (Item item : change.getAddedSubList()){
                    itemAjouter=item;

                    VueItem newItem;
                    if (item instanceof Piece){
                        newItem=new VuePiece(environnement,item);
                        vueItems.add(newItem);
                    }
                    else if (item instanceof Bombe){
                        newItem=new VueBombe(environnement,item);
                        vueItems.add(newItem);
                    }else if (item instanceof CoeurRouge){
                        newItem=new VueCoeurRouge(environnement,item);
                        vueItems.add(newItem);
                    }

                }
            }
            else if (change.wasRemoved()){
                for (Item item : change.getRemoved()){
                    itemsupprimer=item;
                    this.environnement.getChildren().remove(environnement.lookup("#"+item.getId()));
                    this.environnement.getChildren().remove(environnement.lookup("#O"+item.getId()));
                }
            }
        }
    }

    public Item getItemAjouter() {
        return itemAjouter;
    }

    public Item getItemsupprimer() {
        return itemsupprimer;
    }

    public ArrayList<VueItem> getVueItems() {
        return vueItems;
    }
}
