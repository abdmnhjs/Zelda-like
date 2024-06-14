package com.example.sae_zeldalike.modele.Projectile;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class BouleDeFeu extends Projectile {


    public BouleDeFeu(Environnement environnement, int positionX, int positionY, int vitesse,String direction) {
        super(environnement, positionX, positionY, 32, 32, vitesse,10,direction);

    }

    @Override
    public boolean peutEncoreSeDeplacer(){
        boolean peut=false;
        int newX=getPositionX();
        int newY=getPositionY();


        switch (getDirection()){
            case "UP"->{
                newY=getPositionY()-getVitesse();
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))) {
                        peut = true;
                    }
            }
            case "RIGHT"->{
                newX=getPositionX()+getVitesse();
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))){
                        peut = true;
                    }
            }
            case "DOWN"->{
                newY=getPositionY()+getVitesse();
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))){
                        peut = true;
                    }
            }
            case "LEFT"->{
                newX=getPositionX()-getVitesse();
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))){
                        peut = true;
                    }
            }
        }
        return peut;

    }
}
