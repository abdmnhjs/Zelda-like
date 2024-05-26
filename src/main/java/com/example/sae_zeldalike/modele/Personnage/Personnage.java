package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Hitbox;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Personnage {

    private String id;
    private static int compteurPersonnage=0;
    private IntegerProperty pointVie;
    private IntegerProperty pointAttaque;
    private IntegerProperty positionX;
    private IntegerProperty positionY;
    private IntegerProperty vitesseDeplacement;
    protected Environnement environnement;
    private StringProperty direction;
    private final int largeur;
    private final int longueur;

    public Personnage( int pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY, int vitesseDeplacement, int longueur, int largeur) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = new SimpleIntegerProperty(pointVie);
        this.pointAttaque = new SimpleIntegerProperty(pointAttaque);
        this.positionX = new SimpleIntegerProperty(positionX);
        this.positionY = new SimpleIntegerProperty(positionY);
        this.vitesseDeplacement = new SimpleIntegerProperty(vitesseDeplacement);
        this.environnement = environnement;
        this.direction = new SimpleStringProperty("DOWN");
        this.longueur=longueur;
        this.largeur=largeur;
    }

   /* public Personnage( int pointVie, int pointAttaque, Environnement environnement, int vitesseDeplacement) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = new SimpleIntegerProperty(pointVie);
        this.pointAttaque = new SimpleIntegerProperty(pointAttaque);
        this.environnement = environnement;
        Random random = new Random();
        this.positionX = new SimpleIntegerProperty(random.nextInt(environnement.getMap().getColonne() - 1));
        this.positionY = new SimpleIntegerProperty(random.nextInt(environnement.getMap().getLigne() - 1));
        this.vitesseDeplacement = new SimpleIntegerProperty(vitesseDeplacement);
    }**/

    private int getLargeur(){return largeur;}
    private int getLongueur(){return longueur;}
    public StringProperty getDirectionProperty() {
        return direction;
    }
    public String getDirection() {
        return direction.getValue();
    }
    public void setDirection(String direction){
        this.direction.setValue(direction);
    }

    public int getPositionX() {
        return positionX.getValue();
    }
    public IntegerProperty getPositionXProperty() {
        return positionX;
    }
    public void setPositionXProperty(int x){
        positionX.setValue(x);
    }

    public int getPositionY() {
        return positionY.getValue();
    }
    public IntegerProperty getPositionYProperty() {
        return positionY;
    }
    public void setPositionYProperty(int y) {
        positionY.setValue(y);
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

    public Environnement getEnvironnement() {
        return environnement;
    }

    public boolean estDevantObstacle(int x, int y) {
        for (int i = 0; i < this.environnement.getMap().getCoordonnéesTuilesNonTraversables().size(); i++) {
            if (x < this.environnement.getMap().getCoordonnéesTuilesNonTraversables().get(i)[1] + 32 - 10 &&
                    x + this.longueur - 10 > this.environnement.getMap().getCoordonnéesTuilesNonTraversables().get(i)[1] &&
                    y < this.environnement.getMap().getCoordonnéesTuilesNonTraversables().get(i)[0] + 32 - 10 &&
                    y + this.largeur - 10 > this.environnement.getMap().getCoordonnéesTuilesNonTraversables().get(i)[0]) {
                return true;
            }
        }
        return false;
    }

    public boolean estDansLimiteTerrain(int x,int y){
        return (x < 0 || x > this.environnement.getMap().getColonne()*32 || y < 0 || y > this.environnement.getMap().getLigne()*32 || x+32 > this.environnement.getMap().getColonne()*32 ||
                y+32 > this.environnement.getMap().getLigne()*32);

    }

    public Hitbox hitbox(int x,int y){
        Hitbox hitbox = new Hitbox(x,y,getLargeur(),getLongueur());
        return hitbox;
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
