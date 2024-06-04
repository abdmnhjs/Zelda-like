package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Hitbox;
import com.example.sae_zeldalike.modele.Personnage.Ennemi;
import com.example.sae_zeldalike.modele.Personnage.Ennemi1;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Flèche extends Arme {
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Environnement environnement;
    private IntegerProperty vitesseProperty;
    private int longueur;
    private int largeur;
    private String direction;
    private static int compteurFleche = 0;
    private String id;
    private Arc arc;

    public Flèche(int x, int y,  Environnement environnement, Arc arc){
        super(50, 0);
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.vitesseProperty = new SimpleIntegerProperty(30);
        this.environnement = environnement;
        this.longueur = 20;
        this.largeur = 20;
        this.direction = "N";
        this.id = "F"+compteurFleche;
        this.arc = arc;
        compteurFleche++;
    }

//    public boolean estDevantObstacle(int x, int y) {
//        for (int i = 0; i < this.environnement.getMap().getCoordonnéesTuilesNonTraversables().size(); i++) {
//            if (x < this.environnement.getMap().getCoordonnéesTuilesNonTraversables().get(i)[1] + 32 - 10 &&
//                    x + this.longueur - this.longueur*0.3 > this.environnement.getMap().getCoordonnéesTuilesNonTraversables().get(i)[1] &&
//                    y < this.environnement.getMap().getCoordonnéesTuilesNonTraversables().get(i)[0] + 32 - 10 &&
//                    y + this.largeur - this.longueur*0.3 > this.environnement.getMap().getCoordonnéesTuilesNonTraversables().get(i)[0]) {
//                return true;
//            }
//        }
//        return false;
//    }



    public String getId() {
        return this.id;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void seDeplacerHaut(){
        this.yProperty.setValue(this.yProperty.getValue() - this.vitesseProperty.getValue());
    }

    public void seDeplacerBas(){
        this.yProperty.setValue(this.yProperty.getValue() + this.vitesseProperty.getValue());
    }

    public boolean depasseRayon(){
        int rayonAttaque = this.arc.getRayonAttaque();
        if(this.getX() < this.getX() - rayonAttaque || this.getX() > this.getX() + rayonAttaque ||
                this.getY() < this.getY() - rayonAttaque || this.getY() > this.getY() + rayonAttaque){
            return true;
        }
        return false;
    }


    public Hitbox hitbox(int x, int y){
        Hitbox hitbox = new Hitbox(x,y,getLargeur(),getLongueur());
        return hitbox;
    }

    public void seDeplacerDroite(){
        this.xProperty.setValue(this.xProperty.getValue() + this.vitesseProperty.getValue());
    }

    public void seDeplacerGauche(){
        this.xProperty.setValue(this.xProperty.getValue() - this.vitesseProperty.getValue());
    }

    public Environnement getEnvironnement() {
        return this.environnement;
    }

    public boolean estSurEnnemi(Personnage ennemi){
        if(this.getX() < ennemi.getPositionX() + ennemi.getLargeur() &&
                this.getX() + this.getLargeur() > ennemi.getPositionX() &&
                this.getY() < ennemi.getPositionY() + ennemi.getLongueur() &&
                this.getY() + this.getLongueur() > ennemi.getPositionY()){
            return true;
        }
        return false;
    }

    public int getLongueur() {
        return this.longueur;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public int getVitesse() {
        return vitesseProperty.getValue();
    }

    public IntegerProperty getXProperty() {
        return xProperty;
    }

    public int getX(){
        return xProperty.getValue();
    }

    public void setxProperty(int xProperty) {
        this.xProperty.set(xProperty);
    }

    public IntegerProperty getYProperty() {
        return yProperty;
    }

    public int getY(){
        return yProperty.getValue();
    }

    public void setyProperty(int yProperty) {
        this.yProperty.set(yProperty);
    }
}
