package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Hitbox;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile extends Arme {
    private IntegerProperty vitesseProperty;
    private static int compteurFleche = 0;
    private Arc arc;
    protected String id;


    public Projectile(int x, int y, Environnement environnement, Arc arc){
        super(50, 0, environnement);
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.vitesseProperty = new SimpleIntegerProperty(30);
        this.longueur = 20;
        this.largeur = 20;
        this.direction = "N";
        this.id = "F"+compteurFleche;
        this.arc = arc;
        this.initialX = x;
        this.initialY = y;
        compteurFleche++;
    }

    public Projectile(int x, int y, Environnement environnement, int vitesse, int longueur, int largeur){
        super(50, 0, environnement);
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.vitesseProperty = new SimpleIntegerProperty(vitesse);
        this.longueur = longueur;
        this.largeur = largeur;
        this.direction = "N";
        this.initialX = x;
        this.initialY = y;
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

    public int getVitesse() {
        return vitesseProperty.getValue();
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Flèche{" +
                "vitesseProperty=" + vitesseProperty +
                ", arc=" + arc +
                ", id='" + id + '\'' +
                '}';
    }
}
