package com.example.sae_zeldalike.modele.Personnage.Ennemi;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.beans.property.IntegerProperty;

import java.util.Random;

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

            for (int i = compteur; i < 5; i++) {

                Ennemi2 ennemiAjouté = new Ennemi2(environnement);

//                genererPositionAleatoires(ennemiAjouté);

                getEnvironnement().ajouterPersonnage(ennemiAjouté);

            }
        }
    }

    public void genererPositionAleatoires(Personnage p){
        Random random = new Random();
        int posX,posY;
        do{
            posX = (random.nextInt(101))-50;
            posY = (random.nextInt(101))-50;

        }while (!getEnvironnement().estDansTuile(11,p.hitbox(getPositionX()+posX,getPositionY()+posY)));
        p.setPositionXProperty(posX);
        p.setPositionYProperty(posY);
    }

}