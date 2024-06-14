package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

import java.util.*;

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
        int tailleTuile = 32;
        int[] start = {yDepart, xDepart};
        int[] destination = {yArrivee, xArrivee};
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Map<int[], int[]> predecesseurs = new HashMap<>();
        LinkedList<int[]> queue = new LinkedList<>();
        ArrayList<int[]> parcours = new ArrayList<>();

        queue.addFirst(start);
        parcours.add(start);
        predecesseurs.put(start, null);

        while (!queue.isEmpty()) {
            int[] current = queue.removeFirst();

            for (int[] direction : directions) {
                int newX = current[1] + direction[1] * tailleTuile;
                int newY = current[0] + direction[0] * tailleTuile;
                int[] newPath = {newY, newX};

                if (newX == xArrivee && newY == yArrivee) {
                    printPath(predecesseurs, destination);
                    return;
                }

                if (!estMarque(newPath, parcours) && accessible(newPath)) {
                    parcours.add(newPath);
                    queue.addLast(newPath);
                    predecesseurs.put(newPath, current);
                }
            }
        }
    }

    public boolean accessible(int[] coord) {
        int[][] map = this.environnement.getMap().getMap();
        int tileX = coord[1] / 32;
        int tileY = coord[0] / 32;

        if (tileY >= 0 && tileY < map.length && tileX >= 0 && tileX < map[tileY].length) {
            if (map[tileY][tileX] == 11) {
                return true;
            }
        }
        return false;
    }

    public boolean estMarque(int[] s, ArrayList<int[]> parcours) {
        for (int[] coord : parcours) {
            if (Arrays.equals(coord, s)) {
                return true;
            }
        }
        return false;
    }

    public void printPath(Map<int[], int[]> predecesseurs, int[] destination) {
        ArrayList<int[]> path = new ArrayList<>();
        int[] current = destination;
        path.add(current);

        while (predecesseurs.containsKey(current)) {
            current = predecesseurs.get(current);
            if (current != null) {
                path.add(current);
            }
        }

        Collections.reverse(path);
        System.out.println(path);
        for (int i = 1; i < path.size(); i++) {
            this.seDeplace(path.get(i)[1], path.get(i)[0]);
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
