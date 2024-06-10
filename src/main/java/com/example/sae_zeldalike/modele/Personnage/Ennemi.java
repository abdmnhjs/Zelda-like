package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

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

    public void bfs(int xDepart, int yDepart, int xArrivee, int yArrivee) {
        int[] s = new int[]{xDepart, yDepart};
        int tailleTuile = this.environnement.getMap().getTailleTuile();
        LinkedList<int[]> fifo = new LinkedList<>();
        HashSet<int[]> visited = new HashSet<>();
        visited.add(s);
        fifo.addFirst(s);

        while (!fifo.isEmpty()) {
            s = fifo.pollFirst();
            if(this.environnement.getMap().getCoordonnéesTuilesTraversables().contains(new int[]{s[0] + tailleTuile, s[1]})){
                    fifo.addFirst(new int[]{s[0]+tailleTuile, s[1]});
                }
            if(this.environnement.getMap().getCoordonnéesTuilesTraversables().contains(new int[]{s[0]-tailleTuile, s[1]})){
                    fifo.addFirst(new int[]{s[0]-tailleTuile, s[1]});
                }
            if(this.environnement.getMap().getCoordonnéesTuilesTraversables().contains(new int[]{s[0], s[1]+tailleTuile})){
                    fifo.addFirst(new int[]{s[0], s[1]+tailleTuile});
                }
            if(this.environnement.getMap().getCoordonnéesTuilesTraversables().contains(new int[]{s[0], s[1]-tailleTuile})){
                    fifo.addFirst(new int[]{s[0], s[1]-tailleTuile});
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
