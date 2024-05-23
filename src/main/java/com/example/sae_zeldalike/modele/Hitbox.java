package com.example.sae_zeldalike.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Hitbox {

    private IntegerProperty x;
    private IntegerProperty y;
    private int largeur;
    private int longueur;

    public Hitbox(int x, int y, int largeur, int longueur) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.largeur=largeur;
        this.longueur=longueur;

    }

    public IntegerProperty getXProperty(){
        return x;
    }
    public int getX(){
        return x.getValue();
    }
    public void setXProperty(int x) {
        this.x.setValue(x);
    }
    public IntegerProperty getYProperty(){
        return y;
    }
    public int getY(){
        return y.getValue();
    }
    public void setYProperty(int y) {
        this.y.setValue(y);
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
