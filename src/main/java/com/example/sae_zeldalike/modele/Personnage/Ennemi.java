package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class Ennemi extends Personnage {
    public Ennemi(int pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY, int vitesseDeplacement, int longueur, int largeur) {
        super(pointVie, pointAttaque, environnement, positionX, positionY, vitesseDeplacement, longueur, largeur);
        this.environnement.getPersonnages().add(this);
    }

    /*

    public void seDeplace() {
        int probaDeplacement = (int) (Math.random() * 11);
        if (probaDeplacement < 1) {
            int probaX = (int) (Math.random() * 5);
            int probaY = (int) (Math.random() * 5);

            if (probaX <= 3) {
                int probaDirectionX = (int) (Math.random() * 2);
                if (probaDirectionX == 0) {
                    setPositionXProperty(getPositionX() - getVitesseDeplacement());
                }
                if (probaDirectionX == 1) {
                    setPositionXProperty(getPositionX() + getVitesseDeplacement());
                }
            }
            if (probaY <= 2) {
                int probaDirectionY = (int) (Math.random() * 2);
                if (probaDirectionY == 0) {
                    setPositionYProperty(getPositionY() - getVitesseDeplacement());
                }
                if (probaDirectionY == 1) {
                    setPositionYProperty(getPositionY() + getVitesseDeplacement());
                }
            }
        }
    }


*/

    public void seDeplace(int cibleX , int cibleY) {

        int directionX = cibleX - getPositionX();
        int directionY = cibleY - getPositionY();


        if (Math.abs(directionX) > Math.abs(directionY)) {

            if (directionX > 0) {
                setPositionXProperty(getPositionX() + getVitesseDeplacement());
            } else if (directionX < 0) {
                setPositionXProperty(getPositionX() - getVitesseDeplacement());
            }
        } else {

            if (directionY > 0) {
                setPositionYProperty(getPositionY() + getVitesseDeplacement());
            } else if (directionY < 0) {
                setPositionYProperty(getPositionY() - getVitesseDeplacement());
            }
        }

    }
}
