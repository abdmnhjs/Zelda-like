package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.Personnage.VueEnnemi1;
import com.example.sae_zeldalike.Vue.Personnage.VuePersonnage;
import com.example.sae_zeldalike.modele.Personnage.Ennemi1;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateurLink implements ListChangeListener<Link> {


    private Pane environnement;

    public ObservateurLink(Pane pane){
        this.environnement=pane;

    }

    @Override
    public void onChanged(Change<? extends Link> change) {

        while (change.next()) {
            if (change.wasRemoved()){
                for(Link link : change.getRemoved()){
                    this.environnement.getChildren().remove(this.environnement.lookup(link.getId()));
                }
                }
            }
        }

    }

