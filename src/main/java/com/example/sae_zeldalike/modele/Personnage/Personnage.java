package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Hitbox;
import com.example.sae_zeldalike.modele.Item.*;
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
    private int initalX;
    private int initalY;
    private Arme armeCourante;
    private Arc arc;
    private Epée epée;
    private DoubleProperty pointViePercent = new SimpleDoubleProperty();

    public Personnage( int pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY, int vitesseDeplacement, int longueur, int largeur) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = new SimpleIntegerProperty(pointVie);
        this.pointAttaque = new SimpleIntegerProperty(pointAttaque);
        this.positionX = new SimpleIntegerProperty(positionX);
        this.initalX = positionX;
        this.positionY = new SimpleIntegerProperty(positionY);
        this.initalY = positionY;
        this.vitesseDeplacement = new SimpleIntegerProperty(vitesseDeplacement);
        this.environnement = environnement;
        this.direction = new SimpleStringProperty("Inactif_DOWN");
        this.longueur=longueur;
        this.largeur=largeur;
        this.pointViePercent.bind(getPointVieProperty().divide(100.0));

        this.armeCourante = null;
        this.arc = null;
        this.epée = null;

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
        this.pointViePercent.bind(getPointVieProperty().divide(100.0));
        this.positionX = new SimpleIntegerProperty();
        this.positionY = new SimpleIntegerProperty();
        genererPositionAleatoires();

    }

    public void genererPositionAleatoires(){
        Random random = new Random();
        int posX,posY;

        int taille = this.environnement.getMap().getCoordonnéesTuilesTraversables().size();


        int[] couple = this.environnement.getMap().getCoordonnéesTuilesTraversables().get(random.nextInt(taille));

        posX = couple[0];
        posY = couple[1];

        setPositionXProperty(posX);
        setPositionYProperty(posY);

        for(Personnage personnage : this.environnement.getPersonnages()){
            if(personnage.getPositionX() == this.getPositionX() || personnage.getPositionY() == this.getPositionY()
            || this.environnement.estDansLimiteTerrain(this.getPositionX(), this.getPositionX(), this.longueur, this.largeur)){
                genererPositionAleatoires();
            }
        }
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
    public int getInitialX(){
        return this.initalX;
    }
    public int getInitialY(){
        return this.initalY;
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

    public double getPointVieEnPercent() {
        return (double) pointVie.get() / 100.0; // Supposons que la vie maximale est 100. Ajustez en fonction de votre logique.
    }

    public void ajouterEpee(Epée epée){
        if(this.epée == null){
            this.epée = epée;
        }
    }

    public Epée getEpee(){
        if(this.epée != null){
            return this.epée;
        }
        return null;
    }

    public void ajouterArc(Arc arc){
        if(this.arc == null){
            this.arc = arc;
        }
    }

    public Arc getArc(){
        if(this.arc != null){
            return this.arc;
        }
        return null;
    }

    public void equiperEpee(){
        this.armeCourante = this.epée;
    }

    public void equiperArc(){
        this.armeCourante = this.arc;
    }

    public boolean epeeEquipee(){
        if(this.armeCourante == this.epée){
            return true;
        }
        return false;
    }

    public boolean arcEquipe(){
        if(this.armeCourante == this.arc){
            return true;
        }
        return false;
    }

    public void tirerFleche() {
        this.arc.tirerFleche();
    }

    public void utiliserEpee(Epée epée){
        this.environnement.getEpeeEnMain().add(epée);
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
