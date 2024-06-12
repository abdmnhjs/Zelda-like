package com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;

import java.util.ArrayList;

public class Epée extends Arme {


    public Epée(Environnement environnement, int positionX, int positionY) {
        super(environnement, positionX, positionY, 32, 32, 10, 32);
    }

    public Epée(Environnement environnement) {
        super(environnement, 32, 32, 10,32);
    }


    @Override
    public void utiliserCapacite() {

        ArrayList<Personnage> dead = new ArrayList<>();

        for (Personnage perso : getEnvironnement().getPersonnages()) {

            if (!(perso instanceof Link)) {

                switch (getDirection()) {

                    case "UP" -> {
                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
                            perso.reduirePointsDeVie(getDégâts());
                            if (getPersonnage() instanceof Link && ((Link)getPersonnage()).getEffets().size()!=0) {
                                ((Link) getPersonnage()).getEffets().get(0).appliquer(perso);
                            }

                        }
                    }
                    case "DOWN" -> {
                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
                            perso.reduirePointsDeVie(getDégâts());
                            if (getPersonnage() instanceof Link && ((Link)getPersonnage()).getEffets().size()!=0) {
                                ((Link) getPersonnage()).getEffets().get(0).appliquer(perso);
                            }
                        }
                    }
                    case "LEFT" -> {
                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
                            perso.reduirePointsDeVie(getDégâts());
                            if (getPersonnage() instanceof Link && ((Link)getPersonnage()).getEffets().size()!=0) {
                                ((Link) getPersonnage()).getEffets().get(0).appliquer(perso);
                            }
                        }
                    }
                    case "RIGHT" -> {
                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
                            perso.reduirePointsDeVie(getDégâts());
                            if (getPersonnage() instanceof Link && ((Link)getPersonnage()).getEffets().size()!=0) {
                                ((Link) getPersonnage()).getEffets().get(0).appliquer(perso);
                            }
                        }
                    }

                }

            }
            if (!perso.estVivant()){
                dead.add(perso);
            }
        }
        System.out.println(dead.size());
        for (Personnage perso : dead) {
            perso.getEnvironnement().supprimerPersonnage(perso);
        }

    }
}
