package com.example.sae_zeldalike.modele.Projectile;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arc;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;

import java.util.ArrayList;

public class Fleche extends Projectile {

    private Arc arc;

    public Fleche(Environnement environnement, int positionX, int positionY, int largeur, int longueur, int vitesse,Arc arc,String direction) {
        super(environnement, positionX, positionY, largeur, longueur, vitesse,0,direction);
        this.arc=arc;
        this.setDegats(arc.getDégâts());

    }

    public boolean peutEncoreSeDeplacer(){
        boolean peut=false;
        int newX=getPositionX();
        int newY=getPositionY();


        switch (getDirection()){
            case "UP"->{
                newY=getPositionY()-getVitesse();
                if (getPositionInitaleY() - (getPositionY() - getVitesse()) <= arc.getRayonAttaque()) {
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))){
                        peut = true;
                    }



                }
            }
            case "RIGHT"->{
                newX=getPositionX()+getVitesse();
                if ((getPositionX()+getVitesse())-getPositionInitaleX()<=arc.getRayonAttaque()){
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))){
                        peut = true;
                    }

                }
            }
            case "DOWN"->{
                newY=getPositionY()+getVitesse();
                if ((getPositionY()+getVitesse())-getPositionInitaleY()<=arc.getRayonAttaque()){
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))){
                        peut = true;
                    }

                }
            }
            case "LEFT"->{
                newX=getPositionX()-getVitesse();
                if (getPositionInitaleX() - (getPositionX() - getVitesse()) <= arc.getRayonAttaque()) {
                    if (!getEnvironnement().estDevantObstacle(hitbox(newX,newY))){
                        peut = true;
                    }
                }
            }
        }
        return peut;

    }

    @Override
    public void estDevantEnnemi() {
        ArrayList<Personnage> dead = new ArrayList<>();
        boolean finito=false;
        for (Personnage personnage : getEnvironnement().getPersonnages()){
            if (! (personnage instanceof Link)){
                if (getEnvironnement().estDansLaZone(this.hitbox(getPositionX(),getPositionY()),personnage.hitbox(personnage.getPositionX(),personnage.getPositionY()))&& !finito){
                    faireDegats(personnage);
                    if (!personnage.estVivant()){
                        dead.add(personnage);
                    }
                    finito=true;
                }
            }
        }
        for (Personnage perso : dead) {
            perso.getEnvironnement().supprimerPersonnage(perso);
        }
    }


}
