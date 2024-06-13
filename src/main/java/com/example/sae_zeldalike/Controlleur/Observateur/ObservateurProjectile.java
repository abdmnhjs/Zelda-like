package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.Personnage.VueEnnemi1;
import com.example.sae_zeldalike.Vue.Personnage.VueEnnemi2;
import com.example.sae_zeldalike.Vue.Personnage.VuePersonnage;
import com.example.sae_zeldalike.Vue.Projectile.VueFleche;
import com.example.sae_zeldalike.Vue.Projectile.VueProjectile;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi1;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi2;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import com.example.sae_zeldalike.modele.Projectile.BouleDeFeu;
import com.example.sae_zeldalike.modele.Projectile.Fleche;
import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ObservateurProjectile implements ListChangeListener<Projectile> {

    private Pane environnement;
    private ArrayList<VueProjectile> vueProjectiles;

    public ObservateurProjectile(Pane pane, ArrayList<VueProjectile> vueProjectiles){
        this.environnement=pane;
        this.vueProjectiles=vueProjectiles;
    }

    @Override
    public void onChanged(Change<? extends Projectile> change) {
        while (change.next()) {
            if (change.wasAdded()){
                for (Projectile projectile : change.getAddedSubList()){
                    VueProjectile newProjectile;

                    if (projectile instanceof Fleche){
                        newProjectile = new VueFleche(environnement,projectile);
                        vueProjectiles.add(newProjectile);
                    }if (projectile instanceof BouleDeFeu){
                    }
                }
            }
            if (change.wasRemoved()){
                for (Projectile projectile : change.getRemoved()){
//                    System.out.println(projectile.getId());
                    this.environnement.getChildren().remove(environnement.lookup("#"+projectile.getId()));
                }
            }
        }
    }
}
