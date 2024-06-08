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
import java.util.Random;

public abstract class Personnage {

    private String id;
    private static int compteurPersonnage=0;
    protected IntegerProperty pointVie;
    protected IntegerProperty pointAttaque;
    private IntegerProperty positionX;
    private IntegerProperty positionY;
    private IntegerProperty vitesseDeplacement;
    protected Environnement environnement;
    private StringProperty direction;
    private final int largeur;
    private final int longueur;
    private ArrayList<Arme> armes;

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
        this.armes = new ArrayList<>();

    }

    public Personnage( int pointVie, int pointAttaque, Environnement environnement, int vitesseDeplacement, int longueur, int largeur) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = new SimpleIntegerProperty(pointVie);
        this.pointAttaque = new SimpleIntegerProperty(pointAttaque);
        this.vitesseDeplacement = new SimpleIntegerProperty(vitesseDeplacement);
        this.environnement = environnement;
        this.direction = new SimpleStringProperty("Inactif_DOWN");
        this.longueur=longueur;
        this.largeur=largeur;
        this.armes = new ArrayList<>();
        this.positionX = new SimpleIntegerProperty();
        this.positionY = new SimpleIntegerProperty();
        genererPositionAleatoires();

    }

    public void genererPositionAleatoires(){
        Random random = new Random();
        int posX,posY;
        do{
            posX = (random.nextInt((getEnvironnement().getMap().getColonne()-2)*getEnvironnement().getMap().getTailleTuile()));
            posY = (random.nextInt((getEnvironnement().getMap().getLigne()-2)*getEnvironnement().getMap().getTailleTuile()));

//            System.out.println("posX="+posX+",posY="+posY);
        }while (!getEnvironnement().estDansTuile(11,hitbox(posX,posY)));
        setPositionXProperty(posX);
        setPositionYProperty(posY);
    }

    public void tue(){
        setPointVie(0);
    }
    public boolean estVivant(){
        return getPointVie()>0;
    }
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
    public void setPointVie(int pointVie) {
        this.pointVie.set(pointVie);
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


    public Hitbox hitbox(int x,int y){
        Hitbox hitbox = new Hitbox(x,y,getLargeur(),getLongueur());
        return hitbox;
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
