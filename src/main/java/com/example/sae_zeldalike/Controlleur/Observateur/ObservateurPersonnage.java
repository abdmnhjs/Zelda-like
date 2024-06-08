package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.Personnage.VueEnnemi1;
import com.example.sae_zeldalike.Vue.Personnage.VuePersonnage;
import com.example.sae_zeldalike.modele.Personnage.Ennemi1;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateurPersonnage implements ListChangeListener<Personnage> {

    private Pane environnement;
    private ArrayList<VuePersonnage> vuePersonnages;

    public ObservateurPersonnage(Pane pane,ArrayList<VuePersonnage> vuePersonnages){
        this.environnement=pane;
        this.vuePersonnages=vuePersonnages;
    }

    @Override
    public void onChanged(Change<? extends Personnage> change) {

        while (change.next()) {
            if (change.wasAdded()){
                for (Personnage personnage : change.getAddedSubList()){

                    VuePersonnage newPerso;
                    if (personnage instanceof Ennemi1){
                        newPerso =new VueEnnemi1(environnement,personnage);
                        vuePersonnages.add(newPerso);
                    }
                }
            }
            if (change.wasRemoved()){
                for (Personnage personnage : change.getRemoved()){

                    this.environnement.getChildren().remove(environnement.lookup("#"+personnage.getId()));
                    this.environnement.getChildren().remove(environnement.lookup("#O"+personnage.getId()));
                    this.environnement.getChildren().remove(environnement.lookup("#L"+personnage.getId()));
                    this.environnement.getChildren().remove(environnement.lookup("#BCKGL"+personnage.getId()));
                }
            }
        }

    }
}
