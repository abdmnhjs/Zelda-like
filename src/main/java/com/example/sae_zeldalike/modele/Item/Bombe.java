package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Personnage;

import java.util.ArrayList;

public class Bombe extends Item implements Stockable {

    private int degat;
    private int rayonAttaque;

    public Bombe(Environnement environnement, int positionX, int positionY) {
        super(environnement, positionX, positionY,48,48);
        this.degat = 0;
        this.rayonAttaque = getLargeur();
    }

    public Bombe(Environnement environnement) {
        super(environnement,48,48);
        this.degat = 0;
        this.rayonAttaque = getLargeur();
    }

    public void explose(){

        ArrayList<Personnage>dead = new ArrayList<>();
        System.out.println("Bombe va exploser");
        System.out.println("Bombe X= "+this.getPositionX()+" Y= "+this.getPositionY());
        for (Personnage personnage : this.getEnvironnement().getPersonnages()) {
            System.out.println("Perso X= "+personnage.getPositionX()+" Y= "+personnage.getPositionY());
            if ((this.getPositionY() - getRayonAttaque() <= personnage.getPositionY() && personnage.getPositionY() <= this.getPositionY() + getRayonAttaque())
                    && (this.getPositionX() - getRayonAttaque() <= personnage.getPositionX() && personnage.getPositionX() <= this.getPositionX() + getRayonAttaque())) {
                System.out.println(personnage.getClass()+ "est mort");
                personnage.tue();
                if (!personnage.estVivant()){
                    dead.add(personnage);
                }

            }
        }

        for (Personnage personnage : dead) {
            personnage.getEnvironnement().supprimerPersonnage(personnage);
        }
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

}
