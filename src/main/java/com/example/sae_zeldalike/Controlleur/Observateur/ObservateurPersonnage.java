package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurPersonnage implements ListChangeListener<Personnage> {


    private Pane environnement;

    public ObservateurPersonnage(Pane pane){
        this.environnement=pane;
    }
    @Override
    public void onChanged(Change<? extends Personnage> change) {

        while (change.next()) {
//            if (change.wasAdded()){
//                for (Personnage personnage : change.getAddedSubList()){
//                    creerSprite(acteur);
//                }
//            }
            if (change.wasRemoved()){
                for (Personnage personnage : change.getRemoved()){
                        this.environnement.getChildren().remove(environnement.lookup("#"+personnage.getId()));
                }
            }
        }

    }
}
