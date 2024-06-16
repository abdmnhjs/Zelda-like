package com.example.sae_zeldalike.modele.Personnage.Ennemi;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.beans.property.IntegerProperty;

public class Magicien extends Ennemi {

    private Environnement environnement;
    private int compteur;

    public Magicien(Environnement environnement) {
        super(1000, 1, environnement, 2, 32, 32);

        this.environnement = environnement;
    }

    public void invocationPersonnage() {
        compteur = 0;

        for (Personnage personnage : environnement.getPersonnages()) {
            if (personnage instanceof Ennemi2) {
                compteur++;
            }
        }

        if (compteur < 5 && this.estVivant()) {


            int[][] coordonnées = {{50, 150}, {150, 250}, {350, 150}, {150, 50}, {100, 150}};

            for (int i = 0; i < 5; i++) {

                Ennemi2 ennemiAjouté = new Ennemi2(environnement);

                int positionX = coordonnées[i][0];
                int positionY = coordonnées[i][1];

                ennemiAjouté.setPositionXProperty(positionX);
                ennemiAjouté.setPositionYProperty(positionY);

                environnement.ajouterPersonnage(ennemiAjouté);

            }
        }
    }

}