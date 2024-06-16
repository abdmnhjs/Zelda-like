package com.example.sae_zeldalike.modele.Personnage.Ennemi;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class Ennemi1 extends Ennemi {

    public Ennemi1(Environnement environnement, int positionX, int positionY) {
        super(300, 1, environnement, positionX, positionY, 5, 32,32);
    }

    public Ennemi1(Environnement environnement) {
        super(300, 1, environnement,  5, 32,32);
    }



//    public void seDeplace(int cibleX, int cibleY) {
//
//        int vitesseActuelle = getVitesseDeplacement();
//
//        if (estToucheParEffet() && dureeEffet > 0) {
//            if (vitesseActuelle-effetRalentissement>0){
//                vitesseActuelle -= effetRalentissement;
//            }else {
//                vitesseActuelle=1;
//            }
//
//            dureeEffet--;
//            if (dureeEffet <= 0) {
//                setEstToucheParEffet(false);
//                effetRalentissement = 0;
//            }
//        }
//
//        int directionX = cibleX - getPositionX();
//        int directionY = cibleY - getPositionY();
//
//        if (Math.abs(directionX) > Math.abs(directionY)) {
//            if (directionX > 0) {
//                setPositionXProperty(getPositionX() + vitesseActuelle);
//            } else if (directionX < 0) {
//                setPositionXProperty(getPositionX() - vitesseActuelle);
//            }
//        } else {
//            if (directionY > 0) {
//                setPositionYProperty(getPositionY() + vitesseActuelle);
//            } else if (directionY < 0) {
//                setPositionYProperty(getPositionY() - vitesseActuelle);
//            }
//        }
//    }


}
