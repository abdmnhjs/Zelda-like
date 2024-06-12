package com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import com.example.sae_zeldalike.modele.Projectile.Projectile;

import java.util.ArrayList;

public class Arc extends Arme {
    private ArrayList<Projectile> fleches;

    public Arc(Environnement environnement, int positionX, int positionY, int largeur, int longueur, int degats, int rayonAttaque) {
        super(environnement, positionX, positionY, largeur, longueur, degats, rayonAttaque);
        fleches = new ArrayList<>();
    }

    public Arc(Environnement environnement, int largeur, int longueur, int degats, int rayonAttaque) {
        super(environnement, largeur, longueur, degats, rayonAttaque);
        fleches = new ArrayList<>();
    }

    @Override
    public void utiliserCapacite() {
//        ArrayList<Personnage>dead = new ArrayList<>();
//
//        for (Personnage perso : getEnvironnement().getPersonnages()) {
//
//            if (!(perso instanceof Link)) {
//
//                switch (getDirection()) {
//
//                    case "UP" -> {
//                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
//                            perso.reduirePointsDeVie(getDégâts());
//                        }
//
//
//                    }
//                    case "DOWN" -> {
//                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
//                            perso.reduirePointsDeVie(getDégâts());
//                        }
//                        System.out.println("Attaque en B");
//                    }
//                    case "LEFT" -> {
//                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
//                            perso.reduirePointsDeVie(getDégâts());
//                        }
//                        System.out.println("Attaque a G");
//                    }
//                    case "RIGHT" -> {
//                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
//                            perso.reduirePointsDeVie(getDégâts());
//                        }
//                        System.out.println("Attaque a D");
//                    }
//                }
//            }
//            if (!perso.estVivant()){
//                dead.add(perso);
//            }
//        }
//        System.out.println(dead.size());
//        for (Personnage perso : dead) {
//            perso.getEnvironnement().supprimerPersonnage(perso);
//        }

    }


    public void tirerFleche(){
        this.getEnvironnement().getFlèchesEnDéplacement().add(this.fleches.get(0));
        this.fleches.remove(0);
    }

    public ArrayList<Projectile> getFleches() {
        return this.fleches;
    }

}
