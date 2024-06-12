package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.Item.*;
import com.example.sae_zeldalike.Vue.Item.Arme.VueArc;
import com.example.sae_zeldalike.Vue.Item.Arme.VueEpee;
import com.example.sae_zeldalike.modele.Item.*;
import com.example.sae_zeldalike.modele.Item.NonStockable.CoeurBleu;
import com.example.sae_zeldalike.modele.Item.NonStockable.CoeurRouge;
import com.example.sae_zeldalike.modele.Item.NonStockable.Poison;
import com.example.sae_zeldalike.modele.Item.NonStockable.SuperMegaFast;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arc;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Epée;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Bombe;
import com.example.sae_zeldalike.modele.Item.StockableDansPortefeuille.Piece;
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
                    }else if (item instanceof CoeurBleu){
                        newItem=new VueCoeurBleu(environnement,item);
                        vueItems.add(newItem);
                    }else if (item instanceof SuperMegaFast){
                        newItem=new VueSuperMegaFast(environnement,item);
                        vueItems.add(newItem);
                    }else if (item instanceof Poison){
                        newItem=new VuePoison(environnement,item);
                        vueItems.add(newItem);
                    }else if (item instanceof Epée){
                        newItem=new VueEpee(environnement,item);
                        vueItems.add(newItem);
                    }else if (item instanceof Arc){
                        newItem = new VueArc(environnement,item);
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
