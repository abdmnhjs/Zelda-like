package com.example.sae_zeldalike.modele.Projectile;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Hitbox;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arc;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arme;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public abstract class Projectile{

    private Environnement environnement;
    private static int compteurProjectile = 0;
    protected String id;
    protected IntegerProperty positionX;
    protected IntegerProperty positionY;
    private final int positionInitaleX;
    private final int positionInitaleY;
    protected final int largeur;
    protected final int longueur;
    protected IntegerProperty vitesse;
    protected final String direction;
    private int degats;


    public Projectile(Environnement environnement, int positionX,int positionY,int largeur,int longueur,int vitesse,int degats,String direction){
        this.environnement=environnement;
        this.positionX=new SimpleIntegerProperty(positionX);
        this.positionY=new SimpleIntegerProperty(positionY);
        this.id="PROJECTILE"+compteurProjectile;
        compteurProjectile++;
        this.largeur=largeur;
        this.longueur=longueur;
        this.positionInitaleX=positionX;
        this.positionInitaleY=positionY;
        this.vitesse = new SimpleIntegerProperty(vitesse);
        this.direction = direction;
        this.degats=degats;

    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public String getDirection() {
        return direction;
    }


    public void setDirection(String direction) {
        direction = direction;
    }


    public void setPositionX(int positionX) {
        this.positionX.set(positionX);
    }

    public void setPositionY(int positionY) {
        this.positionY.set(positionY);
    }

    public void setVitesse(int vitesse) {
        this.vitesse.set(vitesse);
    }

    public int getLargeur() {
        return largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getPositionX() {
        return positionX.get();
    }

    public IntegerProperty getPositionXProperty() {
        return positionX;
    }

    public int getPositionY() {
        return positionY.get();
    }

    public IntegerProperty getPositionYProperty() {
        return positionY;
    }

    public int getPositionInitaleX() {
        return positionInitaleX;
    }

    public int getPositionInitaleY() {
        return positionInitaleY;
    }

    public IntegerProperty getVitesseProperty() {
        return vitesse;
    }

    public void seDeplacerHaut(){
        setPositionY(getPositionY()-getVitesse());
    }

    public void seDeplacerBas(){
        setPositionY(getPositionY()+getVitesse());
    }

    public void faireDégâts(Personnage personnage){

        personnage.reduirePointsDeVie(getDegats());

        getEnvironnement().supprimerProjectiles(this);
    }


    public abstract boolean peutEncoreSeDeplacer();

    public void deplacement(){

        ArrayList<Personnage> dead = new ArrayList<>();
        boolean finito=false;
        if (peutEncoreSeDeplacer()){
        switch (getDirection()){

            case "UP"->{
                    seDeplacerHaut();

            }
            case "RIGHT"->{
                seDeplacerDroite();
            }
            case "DOWN"->{
                seDeplacerBas();
            }
            case "LEFT"->{
                seDeplacerGauche();
            }
        }

        for (Personnage personnage : getEnvironnement().getPersonnages()){
            if (! (personnage instanceof Link)){
                if (getEnvironnement().estDansLaZone(this.hitbox(getPositionX(),getPositionY()),personnage.hitbox(personnage.getPositionX(),personnage.getPositionY()))&& !finito){
                    faireDégâts(personnage);
                    if (!personnage.estVivant()){
                        dead.add(personnage);
                    }
                    finito=true;
                }
            }
        }
        }else {
            getEnvironnement().supprimerProjectiles(this);
        }
        for (Personnage perso : dead) {
            perso.getEnvironnement().supprimerPersonnage(perso);
        }
    }

    public boolean estSurEnnemi(Personnage ennemi){
        if(this.getPositionX() < ennemi.getPositionX() + ennemi.getLargeur() &&
                this.getPositionX() + this.getLargeur() > ennemi.getPositionX() &&
                this.getPositionY() < ennemi.getPositionY() + ennemi.getLongueur() &&
                this.getPositionY() + this.getLongueur() > ennemi.getPositionY()){
            return true;
        }
        return false;
    }


    public Hitbox hitbox(int x, int y){
        Hitbox hitbox = new Hitbox(x,y,getLargeur(),getLongueur());
        return hitbox;
    }

    public void seDeplacerDroite(){
        setPositionX(getPositionX()+getVitesse());
    }

    public void seDeplacerGauche(){
        setPositionX(getPositionX()-getVitesse());    }

    public Environnement getEnvironnement() {
        return this.environnement;
    }

    public int getVitesse() {
        return vitesse.getValue();
    }

    public String getId() {
        return this.id;
    }




}
