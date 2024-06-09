package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

import java.util.ArrayList;

public class Ennemi extends Personnage {
    protected double probaAttaque;

    public Ennemi(int pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY, int vitesseDeplacement, int longueur, int largeur) {
        super(pointVie, pointAttaque, environnement, positionX, positionY, vitesseDeplacement, longueur,largeur);

    }

    public Ennemi(int pointVie, int pointAttaque, Environnement environnement, int vitesseDeplacement, int longueur, int largeur) {
        super(pointVie, pointAttaque, environnement,  vitesseDeplacement, longueur, largeur);
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

    public boolean proba(double pourcent){
        double x= Math.random();
        double pp=pourcent/100;
        return (x<=pp);
    }

    public void essaieAttaquerJoueur(Link link){
        ArrayList<Link> dead = new ArrayList<>();
        System.out.println(link.getPointVie());
        if(proba(this.probaAttaque)){
            if(this.pointAttaque.getValue() <= link.getPointVie() && this.pointAttaque.getValue() > 0){
                link.setPointVie(link.getPointVie() - this.pointAttaque.getValue());
                if (!link.estVivant()){
                    dead.add(link);
                }

            }
        }

        for (Link linkMort : dead) {
            linkMort.getEnvironnement().supprimerLink(linkMort);
        }
    }

    public boolean estSurJoueur(Link link){
        if(getPositionX() < link.getPositionX() + link.getLargeur() &&
                getPositionY() < link.getPositionY() + link.getLongueur() &&
                getPositionX() + getLargeur() > link.getPositionX() &&
                getPositionY() + getLongueur() > link.getPositionY()){
            return true;
        }
        return false;
    }
}
