package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Hitbox;
import com.example.sae_zeldalike.modele.Item.Arc;
import com.example.sae_zeldalike.modele.Item.Arme;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import com.example.sae_zeldalike.modele.Item.Flèche;
import com.example.sae_zeldalike.modele.Limitations;
import javafx.beans.property.*;

import java.util.ArrayList;

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
    private ArrayList<Arme> armes;
    private DoubleProperty pointViePercent = new SimpleDoubleProperty();

    public Personnage( int pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY, int vitesseDeplacement, int longueur, int largeur) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = new SimpleIntegerProperty(pointVie);
        this.pointAttaque = new SimpleIntegerProperty(pointAttaque);
        this.positionX = new SimpleIntegerProperty(positionX);
        this.positionY = new SimpleIntegerProperty(positionY);
        this.vitesseDeplacement = new SimpleIntegerProperty(vitesseDeplacement);
        this.environnement = environnement;
        this.direction = new SimpleStringProperty("Inactif_DOWN");
        this.longueur=longueur;
        this.largeur=largeur;
        this.pointViePercent.bind(getPointVieProperty().divide(100.0));

        this.armes = new ArrayList<>();

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

    public int getLargeur(){return largeur;}
    public int getLongueur(){return longueur;}

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
    public void setPointDeVie(int pointVie) {
        this.pointVie.set(pointVie);
    }

    public Hitbox hitbox(int x,int y){
        Hitbox hitbox = new Hitbox(x,y,getLargeur(),getLongueur());
        return hitbox;
    }

    public double getPointVieEnPercent() {
        return (double) pointVie.get() / 100.0; // Supposons que la vie maximale est 100. Ajustez en fonction de votre logique.
    }

    public ArrayList<Arme> getArmes() {
        return this.armes;
    }

    public void ajouterArme(Arme arme){
        this.armes.add(arme);
    }

    public void ajouterFlèche(Flèche flèche){
        for(Arme arme : this.armes){
            if(arme instanceof Arc){
                ((Arc) arme).getFlèches().add(flèche);
            }
        }
    }

    public Arc getArc(){
        for(Arme arme : this.armes){
            if(arme instanceof Arc){
                return (Arc) arme;
            }
        }
        return null;
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
