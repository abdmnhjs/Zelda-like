package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Personnage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.image.Image;

public class Bombe extends Item implements Stockable {

    private int degat;
    private int rayonAttaque;
    private boolean effetUtiliser;
    public Bombe(Environnement environnement, int positionX, int positionY) {

        super(environnement, positionX, positionY,48,48);
        this.degat = 25;
        this.rayonAttaque = getLargeur();
        this.effetUtiliser=false;

    }

    public Bombe(Environnement environnement) {
        super(environnement,48,48);
        this.degat = 25;
        this.rayonAttaque = getLargeur();
        this.effetUtiliser=false;
    }


    public void setEffetUtiliser(boolean effetUtiliser) {
        this.effetUtiliser = effetUtiliser;
    }

    public void utiliserCapacite(){


        ArrayList<Personnage>dead = new ArrayList<>();
        System.out.println("Bombe va exploser");
//        System.out.println("Bombe X= "+this.getPositionX()+" Y= "+this.getPositionY());
        for (Personnage personnage : this.getEnvironnement().getPersonnages()) {
//            System.out.println("Perso X= "+personnage.getPositionX()+" Y= "+personnage.getPositionY());
            if ((this.getPositionY() - getRayonAttaque() <= personnage.getPositionY() && personnage.getPositionY() <= this.getPositionY() + getRayonAttaque())
                    && (this.getPositionX() - getRayonAttaque() <= personnage.getPositionX() && personnage.getPositionX() <= this.getPositionX() + getRayonAttaque())) {
//                System.out.println(personnage.getClass()+ "est mort");
                personnage.setPointVie(personnage.getPointVie()-getDegat());
                if (!personnage.estVivant()){
                    dead.add(personnage);
                }

            }
        }

        for (Personnage personnage : dead) {
            personnage.getEnvironnement().supprimerPersonnage(personnage);
        }
        getEnvironnement().supprimerItem(this);
    }

    public int getRayonAttaque() {
        return rayonAttaque;
    }

    public void setRayonAttaque(int rayonAttaque) {
        this.rayonAttaque = rayonAttaque;
    }

    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public boolean effetUtiliser() {
        return effetUtiliser;
    }


}
