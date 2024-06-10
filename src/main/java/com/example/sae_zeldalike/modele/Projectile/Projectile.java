package com.example.sae_zeldalike.modele.Projectile;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Hitbox;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arc;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arme;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile{

    private Environnement environnement;
    private static int compteurProjectile = 0;
    protected String id;
    private IntegerProperty positionX;
    private IntegerProperty positionY;
    private int positionInitaleX;
    private int positionInitaleY;
    private final int largeur;
    private final int longueur;
    private IntegerProperty vitesse;
//    private Arc arc;



    public Projectile(Environnement environnement, int positionX,int positionY,int largeur,int longueur,int vitesse ){
        this.environnement=environnement;
        this.positionX=new SimpleIntegerProperty(positionX);
        this.positionY=new SimpleIntegerProperty(positionY);
        this.id="P"+compteurProjectile;
        compteurProjectile++;
        this.largeur=largeur;
        this.longueur=longueur;
        this.positionInitaleX=positionX;
        this.positionInitaleY=positionY;
        this.vitesse = new SimpleIntegerProperty(vitesse);

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

//    public boolean depasseRayon(){
//        int rayonAttaque = this.arc.getRayonAttaque();
//        if(this.getX() < this.getX() - rayonAttaque || this.getX() > this.getX() + rayonAttaque ||
//                this.getY() < this.getY() - rayonAttaque || this.getY() > this.getY() + rayonAttaque){
//            return true;
//        }
//        return false;
//    }


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
