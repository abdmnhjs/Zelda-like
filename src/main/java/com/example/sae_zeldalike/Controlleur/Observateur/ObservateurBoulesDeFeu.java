package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.VueBouleDeFeu;
import com.example.sae_zeldalike.modele.Projectile.BouleDeFeu;
import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ObservateurBoulesDeFeu implements ListChangeListener<BouleDeFeu> {
    private Pane environnement;
    public ObservateurBoulesDeFeu(Pane environnement) {
        this.environnement = environnement;
    }

    @Override
    public void onChanged(Change<? extends BouleDeFeu> change) {
        while (change.next()){
            if(change.wasAdded()){
                for(Projectile bouleDeFeu : change.getAddedSubList()){
                    new VueBouleDeFeu(bouleDeFeu, this.environnement);
                }
            } else if (change.wasRemoved()){
                for(Projectile bouleDeFeu : change.getRemoved()){
                    System.out.println(this.environnement.lookup("#"+bouleDeFeu.getId()));
                    System.out.println(change.getRemoved());
                    System.out.println(bouleDeFeu.getId());
                    this.environnement.getChildren().remove(this.environnement.lookup("#"+bouleDeFeu.getId()));
                }
            }
        }
    }
}
