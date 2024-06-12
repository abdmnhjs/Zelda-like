package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.Personnage.VuePersonnage;
import com.example.sae_zeldalike.Vue.Projectile.VueProjectile;
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

    }
}
