package com.example.sae_zeldalike.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Hitbox {

    private int x;
    private int y;
    private int largeur;
    private int longueur;



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
    public void setXProperty(int x) {
        this.x=x;
    }
    public int getY(){
        return y;
    }
    public void setYProperty(int y) {
        this.y=y;
    }
    public int getLargeur() {
        return largeur;
    }
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
    public int getLongueur() {
        return longueur;
    }
    public void setLongueur(int longueur){
        this.longueur=longueur;
    }
}
