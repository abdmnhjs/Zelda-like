package com.example.sae_zeldalike.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Hitbox {

    private final int x;
    private final int y;
    private final int largeur;
    private final int longueur;

    public Hitbox(int x, int y, int largeur, int longueur) {
        this.x = x;
        this.y = y;
        this.largeur=largeur;
        this.longueur=longueur;

    }


    public int getXGauche(){
        return (getX()+12)/(getLargeur());
    }
    public int getXDroite(){
        return (getX()+getLargeur()-12)/getLargeur();
    }
    public int getYHaut(){
        return (getY()+7)/(getLongueur());
    }
    public int getYBas(){
        return (getY()+getLongueur()-7)/getLongueur();
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getLargeur() {
        return largeur;
    }
    public int getLongueur() {
        return longueur;
    }
}
