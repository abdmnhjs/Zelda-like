package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class Ennemi1 extends Personnage {

    public Ennemi1(Environnement environnement, int positionX, int positionY) {
        super(100, 1, environnement, positionX, positionY, 5, 32,32);
    }

    public Ennemi1(Environnement environnement) {
        super(100, 1, environnement,  5, 32,32);
    }



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
