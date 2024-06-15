package com.example.sae_zeldalike.modele.Projectile;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi;
import com.example.sae_zeldalike.modele.Personnage.Personnage;

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
                newY-=getVitesse();
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))) {
                        peut = true;
                    }
            }
            case "RIGHT"->{
                newX+=getVitesse();
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))){
                        peut = true;
                    }
            }
            case "DOWN"->{
                newY+=getVitesse();
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))){
                        peut = true;
                    }
            }
            case "LEFT"->{
                newX-=getVitesse();
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))){
                        peut = true;
                    }
            }
        }
        return peut;

    }

    @Override
    public void estDevantEnnemi() {
        boolean finito=false;
        for (Personnage personnage : getEnvironnement().getPersonnages()){
            if (! (personnage instanceof Ennemi)){
                if (getEnvironnement().estDansLaZone(this.hitbox(getPositionX(),getPositionY()),personnage.hitbox(personnage.getPositionX(),personnage.getPositionY()))&& !finito){
                    faireDegats(personnage);
                    finito=true;
                }
            }
        }
    }
}
