package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.Personnage.VueEnnemi2;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi2;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateurEnnemi2 implements ListChangeListener<Ennemi2> {


    private Pane environnement;
    private ArrayList<VueEnnemi2> vueEnnemis2;

    public ObservateurEnnemi2(Pane pane, ArrayList<VueEnnemi2> vueEnnemis2){
        this.environnement=pane;
        this.vueEnnemis2=vueEnnemis2;
    }

    @Override
    public void onChanged(Change<? extends Ennemi2> change) {

        while (change.next()) {
            if (change.wasAdded()){
                for (Ennemi2 personnage : change.getAddedSubList()){

                    VueEnnemi2 newPerso;

                    newPerso =new VueEnnemi2(environnement,personnage);
                    vueEnnemis2.add(newPerso);
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

