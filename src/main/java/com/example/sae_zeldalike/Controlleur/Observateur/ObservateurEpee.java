package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.VueEpee;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Epée;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurEpee implements ListChangeListener<Epée> {
    private Pane environnement;
    private Personnage personnage;
    public ObservateurEpee(Pane environnement, Personnage personnage) {
        this.environnement = environnement;
        this.personnage = personnage;
    }

    @Override
    public void onChanged(Change<? extends Epée> change) {
        while(change.next()) {
            if(change.wasAdded()) {
                for(Epée epee : change.getAddedSubList()) {
                    new VueEpee(epee, this.environnement, this.personnage);
                }
            } else if (change.wasRemoved()) {
                for(Epée epee : change.getRemoved()) {
                    this.environnement.getChildren().remove(this.environnement.lookup("#"+epee.getId()));
                }
            }
        }
    }
}
