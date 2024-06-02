package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.VueFlèche;
import com.example.sae_zeldalike.modele.Item.Flèche;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurFlechesEnDeplacement implements ListChangeListener<Flèche> {
    private Pane environnement;
    public ObservateurFlechesEnDeplacement(Pane environnement) {
        this.environnement = environnement;
    }

    @Override
    public void onChanged(Change<? extends Flèche> change) {
        while (change.next()){
            if(change.wasAdded()){
                for(Flèche fleche : change.getAddedSubList()){
                    new VueFlèche(fleche, this.environnement);
                }
            } else if (change.wasRemoved()){
                for(Flèche fleche : change.getRemoved()){
                    this.environnement.getChildren().remove(this.environnement.lookup("#"+fleche.getId()));
                }
            }
        }
    }
}
