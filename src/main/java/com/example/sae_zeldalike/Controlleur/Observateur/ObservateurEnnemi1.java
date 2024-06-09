package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.Personnage.VueEnnemi1;
import com.example.sae_zeldalike.Vue.Personnage.VuePersonnage;
import com.example.sae_zeldalike.modele.Personnage.Ennemi;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateurEnnemi1 implements ListChangeListener<Ennemi> {


    private Pane environnement;
    private ArrayList<VuePersonnage> vuePersonnages;

    public ObservateurEnnemi1(Pane pane, ArrayList<VuePersonnage> vuePersonnages){
        this.environnement=pane;
        this.vuePersonnages=vuePersonnages;
    }

    @Override
    public void onChanged(Change<? extends Ennemi> change) {

        while (change.next()) {
            if (change.wasAdded()){
                for (Ennemi personnage : change.getAddedSubList()){

                    VuePersonnage newPerso;
                    if (personnage instanceof Ennemi){
                        newPerso =new VueEnnemi1(environnement,personnage);
                        vuePersonnages.add(newPerso);
                    }
                }
            }
            if (change.wasRemoved()){
                for (Ennemi personnage : change.getRemoved()){
                    this.environnement.getChildren().remove(environnement.lookup("#"+personnage.getId()));
                    this.environnement.getChildren().remove(environnement.lookup("#O"+personnage.getId()));
                    this.environnement.getChildren().remove(environnement.lookup("#L"+personnage.getId()));
                    this.environnement.getChildren().remove(environnement.lookup("#BCKGL"+personnage.getId()));
                }
            }
        }

    }
}
