package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.beans.property.IntegerProperty;

import java.util.ArrayList;

public class Arme {
    private int dégâts;
    private int rayonAttaque;
    protected IntegerProperty xProperty;
    protected IntegerProperty yProperty;
    protected int longueur;
    protected int largeur;
    protected String direction;
    protected int initialX;
    protected int initialY;
    protected Environnement environnement;

    public Arme(int dégâts, int rayonAttaque, Environnement environnement) {
        this.dégâts = dégâts;
        this.rayonAttaque = rayonAttaque;
        this.environnement = environnement;
    }

    public void faireDégâts(Personnage personnage, int dégâts){
        ArrayList<Personnage> dead = new ArrayList<>();
        if(dégâts <= personnage.getPointVie() && dégâts > 0){
            personnage.setPointDeVie(personnage.getPointVie() - dégâts);
            if (!personnage.estVivant()){
                dead.add(personnage);
            }

        }
        for (Personnage perso : dead) {
            perso.getEnvironnement().supprimerPersonnage(perso);
        }
    }

    public boolean estSurEnnemi(Personnage ennemi){
        if(this.getX() < ennemi.getPositionX() + ennemi.getLargeur() &&
                this.getX() + this.getLargeur() > ennemi.getPositionX() &&
                this.getY() < ennemi.getPositionY() + ennemi.getLongueur() &&
                this.getY() + this.getLongueur() > ennemi.getPositionY()){
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

    public int getLongueur() {
        return this.longueur;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public IntegerProperty getXProperty() {
        return xProperty;
    }

    public int getX(){
        return xProperty.getValue();
    }

    public void setxProperty(int xProperty) {
        this.xProperty.set(xProperty);
    }

    public IntegerProperty getYProperty() {
        return yProperty;
    }

    public int getY(){
        return yProperty.getValue();
    }

    public void setyProperty(int yProperty) {
        this.yProperty.set(yProperty);
    }
}
