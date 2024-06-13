package com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import com.example.sae_zeldalike.modele.Projectile.Fleche;
import com.example.sae_zeldalike.modele.Projectile.Projectile;

import java.util.ArrayList;

public class Arc extends Arme {


    public Arc(Environnement environnement, int positionX, int positionY) {
        super(environnement, positionX, positionY, 32, 32, 10, 500);

    }

    public Arc(Environnement environnement) {
        super(environnement, 32, 32, 10, 500);

    }

    @Override
    public void utiliserCapacite() {

        switch (getDirection()){
            case "UP"-> {
                getEnvironnement().ajouterProjectiles(new Fleche(getEnvironnement(),(getPositionX()+getLargeur()/2),(getPositionY()+getLongueur()/2),12,24,getPersonnage().getVitesseDeplacement(),this,this.getDirection()));
            }
            case "DOWN"-> {
                getEnvironnement().ajouterProjectiles(new Fleche(getEnvironnement(),(getPositionX()+getLargeur()/2),(getPositionY()+getLongueur()/2),12,24,getPersonnage().getVitesseDeplacement(),this,this.getDirection()));
            }
            case "LEFT"-> {
                getEnvironnement().ajouterProjectiles(new Fleche(getEnvironnement(),(getPositionX()-getLargeur()/2),(getPositionY()+getLongueur()/2),24,12,getPersonnage().getVitesseDeplacement(),this,this.getDirection()));
            }
            case "RIGHT"-> {
                getEnvironnement().ajouterProjectiles(new Fleche(getEnvironnement(),(getPositionX()+getLargeur()/2),(getPositionY()+getLongueur()/2),24,12,getPersonnage().getVitesseDeplacement(),this,this.getDirection()));
            }
        }


    }



}
