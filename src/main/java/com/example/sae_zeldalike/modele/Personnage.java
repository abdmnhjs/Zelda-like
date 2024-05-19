package com.example.sae_zeldalike.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;
public abstract class Personnage {

    private String id;
    private static int compteurPersonnage=0;
    private IntegerProperty pointVie;
    private IntegerProperty pointAttaque;
    private IntegerProperty positionX;
    private IntegerProperty précédentePositionXProperty;
    private IntegerProperty positionY;
    private IntegerProperty précédentePositionYProperty;
    private IntegerProperty vitesseDeplacement;
    protected Environnement environnement;

    public Personnage( int pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY, int vitesseDeplacement) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = new SimpleIntegerProperty(pointVie);
        this.pointAttaque = new SimpleIntegerProperty(pointAttaque);
        this.positionX = new SimpleIntegerProperty(positionX);
        this.positionY = new SimpleIntegerProperty(positionY);
        this.précédentePositionXProperty = new SimpleIntegerProperty(0);
        this.précédentePositionYProperty = new SimpleIntegerProperty(0);
        this.vitesseDeplacement = new SimpleIntegerProperty(vitesseDeplacement);
        this.environnement = environnement;
    }

   public Personnage( int pointVie, int pointAttaque, Environnement environnement, int vitesseDeplacement) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = new SimpleIntegerProperty(pointVie);
        this.pointAttaque = new SimpleIntegerProperty(pointAttaque);
        this.environnement = environnement;
        Random random = new Random();
        this.positionX = new SimpleIntegerProperty(random.nextInt(environnement.getMap().getColonne() - 1));
        this.positionY = new SimpleIntegerProperty(random.nextInt(environnement.getMap().getLigne() - 1));
        this.précédentePositionXProperty = new SimpleIntegerProperty(0);
        this.précédentePositionYProperty = new SimpleIntegerProperty(0);
        this.vitesseDeplacement = new SimpleIntegerProperty(vitesseDeplacement);
    }

    public int getPositionX() {
        return positionX.getValue();
    }
    public IntegerProperty getPositionXProperty() {
        return positionX;
    }
    public void setPositionXProperty(int x){
        this.précédentePositionXProperty.setValue(this.positionX.getValue());
        positionX.setValue(x);
    }

    public IntegerProperty getPrécédentePositionXProperty() {
        return this.précédentePositionXProperty;
    }

    public int getPositionY() {
        return positionY.getValue();
    }
    public IntegerProperty getPositionYProperty() {
        this.précédentePositionYProperty.setValue(this.positionY.getValue());
        return positionY;
    }
    public void setPositionYProperty(int y) {
        positionY.setValue(y);
    }

    public IntegerProperty getPrécédentePositionYProperty() {
        return this.précédentePositionYProperty;
    }

    public int getVitesseDeplacement() {
        return vitesseDeplacement.getValue();
    }
    public IntegerProperty getVitesseDeplacementProperty(){ return vitesseDeplacement;}
    public void setVitesseDeplacementProperty(int v){ vitesseDeplacement.setValue(v);}

    public int getPointVie() {
        return pointVie.getValue();
    }
    public IntegerProperty getPointVieProperty() { return pointVie;}
    public void setPointVieProperty(int degats){
        pointVie.setValue(getPointVie()-degats);
    }

    public int getPointAttaque() {
        return pointAttaque.getValue();
    }
    public IntegerProperty getPointAttaqueProperty(){ return pointAttaque;}
    public void setPointAttaqueProperty(int attaque) { pointAttaque.setValue(attaque);}

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Personnage{" +
                "id='" + id + '\'' +
                ", pointVie=" + pointVie +
                ", pointAttaque=" + pointAttaque +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", vitesseDéplacement=" + vitesseDeplacement +
                ", environnement=" + environnement +
                '}';
    }
}
