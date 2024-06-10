package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.VueFlèche;
import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurFlechesEnDeplacement implements ListChangeListener<Projectile> {
    private Pane environnement;
    public ObservateurFlechesEnDeplacement(Pane environnement) {
        this.environnement = environnement;
    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {
        while (change.next()){
            if(change.wasAdded()){
                for(Projectile fleche : change.getAddedSubList()){
                    new VueFlèche(fleche, this.environnement);
                }
            } else if (change.wasRemoved()){
                for(Projectile fleche : change.getRemoved()){
                    System.out.println(this.environnement.lookup("#"+fleche.getId()));
                    System.out.println(change.getRemoved());
                    System.out.println(fleche.getId());
                    this.environnement.getChildren().remove(this.environnement.lookup("#"+fleche.getId()));
                }
            }
        }
    }
}
