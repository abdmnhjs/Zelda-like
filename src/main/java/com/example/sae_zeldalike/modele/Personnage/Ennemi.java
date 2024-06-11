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
        HashMap<int[], int[]> predecesseurs = new HashMap<>();
        ArrayList<int[]> chemin;
        visited.add(s);
        fifo.addFirst(s);

        while (!fifo.isEmpty()) {
            s = fifo.pollFirst();
            if(this.environnement.getMap().getCoordonnéesTuilesTraversables().contains(new int[]{s[0] + tailleTuile, s[1]})){
                    fifo.addFirst(new int[]{s[0]+tailleTuile, s[1]});
                    predecesseurs.put(s, new int[]{s[0]+tailleTuile, s[1]});
                }
            if(this.environnement.getMap().getCoordonnéesTuilesTraversables().contains(new int[]{s[0]-tailleTuile, s[1]})){
                    fifo.addFirst(new int[]{s[0]-tailleTuile, s[1]});
                    predecesseurs.put(s, new int[]{s[0]-tailleTuile, s[1]});
                }
            if(this.environnement.getMap().getCoordonnéesTuilesTraversables().contains(new int[]{s[0], s[1]+tailleTuile})){
                    fifo.addFirst(new int[]{s[0], s[1]+tailleTuile});
                    predecesseurs.put(s, new int[]{s[0], s[1]+tailleTuile});
                }
            if(this.environnement.getMap().getCoordonnéesTuilesTraversables().contains(new int[]{s[0], s[1]-tailleTuile})){
                    fifo.addFirst(new int[]{s[0], s[1]-tailleTuile});
                    predecesseurs.put(s, new int[]{s[0], s[1]-tailleTuile});
                }
            }
        chemin = parcourirPredecesseur(predecesseurs, xArrivee, yArrivee);
        for(int i = chemin.size() - 1; i >= 0; i--){
            this.seDeplace(chemin.get(i)[0], chemin.get(i)[1]);
        }
        }

        public static ArrayList<int[]> parcourirPredecesseur(HashMap<int[], int[]> map, int xArrivee, int yArrivee){
            int[] actuel = new int[]{xArrivee, yArrivee};
            ArrayList<int[]> couples = new ArrayList<>();
            couples.add(actuel);

            while (actuel != null){
                actuel = map.get(actuel);
                couples.add(actuel);
            }

            return couples;
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
