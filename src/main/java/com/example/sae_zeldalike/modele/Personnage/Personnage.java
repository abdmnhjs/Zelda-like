package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Hitbox;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import javafx.beans.property.*;

public abstract class Personnage {

    private String id;
    private static int compteurPersonnage=0;
    private DoubleProperty pointVie;
    private DoubleProperty pointAttaque;
    private IntegerProperty positionX;
    private IntegerProperty positionY;
    private IntegerProperty vitesseDeplacement;
    protected Environnement environnement;
    private StringProperty direction;
    private final int largeur;
    private final int longueur;
    private DoubleProperty pointViePercent = new SimpleDoubleProperty();

    public Personnage( double pointVie, double pointAttaque, Environnement environnement, int positionX, int positionY, int vitesseDeplacement) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = new SimpleDoubleProperty(pointVie);
        this.pointAttaque = new SimpleDoubleProperty(pointAttaque);
        this.positionX = new SimpleIntegerProperty(positionX);
        this.positionY = new SimpleIntegerProperty(positionY);
        this.vitesseDeplacement = new SimpleIntegerProperty(vitesseDeplacement);
        this.environnement = environnement;
        this.direction = new SimpleStringProperty("Inactif_DOWN");
        this.longueur=32;
        this.largeur=32;
        this.pointViePercent.bind(getPointVieProperty().divide(100.0));
        environnement.ajouterPersonnage(this);

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

    public double getPointVie() {
        return pointVie.getValue();
    }
    public DoubleProperty getPointVieProperty() { return pointVie;}
    public void setPointVieProperty(double degats){
        pointVie.setValue(getPointVie()-degats);
    }


    public double getPointAttaque() {
        return pointAttaque.getValue();
    }
    public DoubleProperty getPointAttaqueProperty(){ return pointAttaque;}
    public void setPointAttaqueProperty(double attaque) { pointAttaque.setValue(attaque);}

    public String getId() {
        return id;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }



    public Hitbox hitbox(int x,int y){
        Hitbox hitbox = new Hitbox(x,y,getLargeur(),getLongueur());
        return hitbox;
    }

    public double getPointVieEnPercent() {
        return (double) pointVie.get() / 100.0; // Supposons que la vie maximale est 100. Ajustez en fonction de votre logique.
    }

    public DoubleProperty pointViePercentProperty() {
        return pointViePercent;
    }

    public double getPointViePercent() {
        return pointViePercent.get();
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
