package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Limitations;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Flèche extends Arme implements Limitations {
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Environnement environnement;
    private IntegerProperty vitesseProperty;
    private int longueur;
    private int largeur;

    public Flèche(int x, int y, int dégâts, Environnement environnement){
        super(dégâts, 0);
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.vitesseProperty = new SimpleIntegerProperty(30);
        this.environnement = environnement;
        this.longueur = 20;
        this.largeur = 20;
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

    @Override
    public boolean estDevantObstacle(int x, int y) {
        return false;
    }

    public boolean estDansLimiteTerrain(int x, int y){

        return (x < 0 || x > this.environnement.getMap().getColonne()*32 || y < 0 || y > this.environnement.getMap().getLigne()*32 || x+32 > this.environnement.getMap().getColonne()*32 ||
                y+32 > this.environnement.getMap().getLigne()*32);

    }

    public void seDeplacerHaut(){
        this.yProperty.setValue(this.yProperty.getValue() - this.vitesseProperty.getValue());
    }

    public void seDeplacerBas(){
        this.yProperty.setValue(this.yProperty.getValue() + this.vitesseProperty.getValue());
    }

    public void seDeplacerDroite(){
        this.xProperty.setValue(this.xProperty.getValue() + this.vitesseProperty.getValue());
    }

    public void seDeplacerGauche(){
        this.xProperty.setValue(this.xProperty.getValue() - this.vitesseProperty.getValue());
    }



    public int getLongueur() {
        return this.longueur;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public int getVitesseProperty() {
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
