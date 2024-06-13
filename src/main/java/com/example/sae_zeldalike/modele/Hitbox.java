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
        if (getLargeur()>30) {
            return (getX() + 12) / (getLargeur());
        }else{
            return (getX())/getLargeur();
        }
    }
    public int getXDroite(){
        if (getLargeur()>30) {
            return (getX() + getLargeur() - 12) / getLargeur();
        }else {
            return (getX()+getLargeur())/getLargeur();
        }
    }
    public int getYHaut(){
        if (getLongueur()>30) {
            return (getY() + 7) / (getLongueur());
        }else {
            return (getY())/getLongueur();
        }
    }
    public int getYBas(){
        if (getLongueur()>30) {
            return (getY() + getLongueur() - 7) / getLongueur();
        }else {
            return (getY()+getLongueur())/getLongueur();
        }
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
