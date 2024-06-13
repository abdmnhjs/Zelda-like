package com.example.sae_zeldalike.modele.Projectile;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arc;

public class Fleche extends Projectile {

    private Arc arc;

    public Fleche(Environnement environnement, int positionX, int positionY, int largeur, int longueur, int vitesse,Arc arc,String direction) {
        super(environnement, positionX, positionY, largeur, longueur, vitesse,0,direction);
        this.arc=arc;
        this.setDegats(arc.getDégâts());

    }

    public boolean peutEncoreSeDeplacer(){
        boolean peut=false;


        switch (getDirection()){
            case "UP"->{
                if (getPositionInitaleY() - (getPositionY() - getVitesse()) <= arc.getRayonAttaque()) {
                        peut = true;

                }
            }
            case "RIGHT"->{
                if ((getPositionX()+getVitesse())-getPositionInitaleX()<=arc.getRayonAttaque()){

                        peut = true;

                }
            }
            case "DOWN"->{
                if ((getPositionY()+getVitesse())-getPositionInitaleY()<=arc.getRayonAttaque()){

                        peut = true;

                }
            }
            case "LEFT"->{
                if (getPositionInitaleX() - (getPositionX() - getVitesse()) <= arc.getRayonAttaque()) {

                        peut = true;
                }
            }
        }
        return peut;

    }




}
