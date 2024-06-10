package com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Stockable;
import com.example.sae_zeldalike.modele.Personnage.Personnage;

import java.util.ArrayList;

public class Arme extends Item implements Stockable {

    private int dégâts;
    private int rayonAttaque;
    protected String direction;
    protected int initialX;
    protected int initialY;

    public Arme(Environnement environnement, int positionX, int positionY, int largeur, int longueur,int degats,int rayonAttaque) {
        super(environnement, positionX, positionY, largeur, longueur);
        this.dégâts=degats;
        this.rayonAttaque=rayonAttaque;
        this.direction=null;
        this.initialX=positionX;
        this.initialY=positionY;

    }

    public Arme(Environnement environnement, int largeur, int longueur,int degats,int rayonAttaque) {
        super(environnement, largeur, longueur);
        this.dégâts=degats;
        this.rayonAttaque=rayonAttaque;
        this.direction=null;
        this.initialX=getPositionX();
        this.initialY=getPositionY();
    }


    public void faireDégâts(Personnage personnage, int dégâts){
        ArrayList<Personnage> dead = new ArrayList<>();
        if(dégâts <= personnage.getPointVie() && dégâts > 0){
            personnage.setPointVie(personnage.getPointVie() - dégâts);
            if (!personnage.estVivant()){
                dead.add(personnage);
            }

        }
        for (Personnage perso : dead) {
            perso.getEnvironnement().supprimerPersonnage(perso);
        }
    }

    public boolean estSurEnnemi(Personnage ennemi){
        if(super.getPositionX() < ennemi.getPositionX() + ennemi.getLargeur() &&
                this.getPositionX() + this.getLargeur() > ennemi.getPositionX() &&
                this.getPositionX() < ennemi.getPositionY() + ennemi.getLongueur() &&
                this.getPositionX() + this.getLongueur() > ennemi.getPositionY()){
            return true;
        }
        return false;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getInitialX() {
        return this.initialX;
    }

    public int getInitialY() {
        return this.initialY;
    }

    public int getDégâts() {
        return this.dégâts;
    }

    public int getRayonAttaque() {
        return this.rayonAttaque;
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public boolean effetUtiliser() {
        return false;
    }

    @Override
    public void utiliserCapacite() {

    }
}
