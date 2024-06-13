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
        Map<List<Integer>, int[]> predecesseurs = new HashMap<>();
        LinkedList<int[]> queue = new LinkedList<>();
        ArrayList<int[]> parcours = new ArrayList<>();
        ArrayList<int[]> path = new ArrayList<>();
        queue.addFirst(start);
        parcours.add(start);
        predecesseurs.put(Arrays.asList(start[0], start[1]), null);

        while (!queue.isEmpty()) {
            int[] actuel = queue.pollLast();

            for (int[] direction : directions) {
                int nx = actuel[1] + direction[1] * tailleTuile;
                int ny = actuel[0] + direction[0] * tailleTuile;
                int[] ntab = {ny, nx};

                if (Arrays.equals(actuel, destination)) {
                    path = printPath(predecesseurs, actuel);
                    return;
                }

                if (accessible(nx, ny) && !estMarque(ntab, parcours)) {
                    parcours.add(ntab);
                    predecesseurs.put(Arrays.asList(ntab[0], ntab[1]), actuel);
                    queue.addFirst(ntab);
                }
            }
        }

        for (int i = 0 ; i < path.size() ; i++) {
            this.seDeplace(path.get(i)[0], path.get(i)[1]);
        }
    }

    public boolean accessible(int x, int y) {
        for (int[] couple : this.environnement.getMap().getCoordonnéesTuilesTraversables()) {
            if (x == couple[1] && y == couple[0]) {
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

    public ArrayList<int[]> printPath(Map<List<Integer>, int[]> predecesseurs, int[] destination) {
        ArrayList<int[]> path = new ArrayList<>();
        for (int[] at = destination; at != null; at = predecesseurs.get(Arrays.asList(at[0], at[1]))) {
            path.add(at);
        }
        Collections.reverse(path);
        System.out.println("Chemin trouvé: " + path);
        return path;
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
