package com.example.sae_zeldalike.modele.Personnage.Ennemi;

import com.example.sae_zeldalike.modele.Projectile.BouleDeFeu;
import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Link;

public class Ennemi2 extends Ennemi{
    private static int compteurEnnemi2 = 0;
    public Ennemi2(Environnement environnement){
        super(200, 40, environnement, 5, 32, 32);
        this.probaAttaque = 80;
    }

    public void essaieTirerBouleDeFeu(Link link){
        if(proba(probaAttaque)){
            tirerProjectileHaut(link);
            tirerProjectileBas(link);
            tirerProjectileDroite(link);
            tirerProjectileGauche(link);
        }
    }

    public void tirerProjectileHaut(Link link) {
        BouleDeFeu bouleDeFeu;

        int ennemiX = this.getPositionX();
        int ennemiY = this.getPositionY();
        int ennemiLargeur = this.getLargeur();

        int linkX = link.getPositionX();
        int linkY = link.getPositionY();
        int linkLargeur = link.getLargeur();
        int linkHauteur = link.getLongueur();

        boolean linkAuDessus = linkY + linkHauteur < ennemiY;
        boolean linkMemeColonne = (linkX >= ennemiX && linkX <= ennemiX + ennemiLargeur) || (linkX + linkLargeur >= ennemiX && linkX + linkLargeur <= ennemiX + ennemiLargeur);

        if(linkAuDessus && linkMemeColonne) {
            System.out.println("Link détecté au-dessus !");
            bouleDeFeu = new BouleDeFeu(ennemiX, ennemiY, this.environnement, 30, 32, 32);
            bouleDeFeu.setDirection("UP");
            this.environnement.ajouterBouleDeFeu(bouleDeFeu);
        }
    }



    public void tirerProjectileBas(Link link) {
        BouleDeFeu bouleDeFeu;

        int ennemiX = this.getPositionX();
        int ennemiY = this.getPositionY();
        int ennemiLargeur = this.getLargeur();
        int ennemiLongueur = this.getLongueur();

        int linkX = link.getPositionX();
        int linkY = link.getPositionY();
        int linkLargeur = link.getLargeur();

        boolean linkEnDessous = linkY > ennemiY + ennemiLongueur;
        boolean linkMemeColonne = (linkX >= ennemiX && linkX <= ennemiX + ennemiLargeur) || (linkX + linkLargeur >= ennemiX && linkX + linkLargeur <= ennemiX + ennemiLargeur);

        if (linkEnDessous && linkMemeColonne) {
            bouleDeFeu = new BouleDeFeu(ennemiX, ennemiY, this.environnement, 30, 32, 32);
            bouleDeFeu.setDirection("DOWN");
            this.environnement.ajouterBouleDeFeu(bouleDeFeu);
        }
    }


    public void tirerProjectileDroite(Link link) {
        BouleDeFeu bouleDeFeu;

        int ennemiX = this.getPositionX();
        int ennemiY = this.getPositionY();
        int ennemiLargeur = this.getLargeur();
        int ennemiLongueur = this.getLongueur();

        int linkX = link.getPositionX();
        int linkY = link.getPositionY();
        int linkLongueur = link.getLongueur();

        // Vérifie si Link est à droite de l'ennemi et dans la même ligne (en prenant en compte les longueurs et hauteurs)
        boolean linkADroite = linkX > ennemiX + ennemiLargeur;
        boolean linkMemeLigne = (linkY >= ennemiY && linkY <= ennemiY + ennemiLongueur) || (linkY + linkLongueur >= ennemiY && linkY + linkLongueur <= ennemiY + ennemiLongueur);

        if (linkADroite && linkMemeLigne) {
            bouleDeFeu = new BouleDeFeu(ennemiX, ennemiY, this.environnement, 30, 32, 32);
            bouleDeFeu.setDirection("RIGHT");
            this.environnement.ajouterBouleDeFeu(bouleDeFeu);
        }
    }


    public void tirerProjectileGauche(Link link) {
        BouleDeFeu bouleDeFeu;

        int ennemiX = this.getPositionX();
        int ennemiY = this.getPositionY();
        int ennemiWidth = this.getLargeur();
        int ennemiLongueur = this.getLongueur();

        int linkX = link.getPositionX();
        int linkY = link.getPositionY();
        int linkLargeur = link.getLargeur();
        int linkLongueur = link.getLongueur();

        boolean linkAGauche = linkX + linkLargeur < ennemiX;
        boolean linkMemeLigne = (linkY >= ennemiY && linkY <= ennemiY + ennemiLongueur) || (linkY + linkLongueur >= ennemiY && linkY + linkLongueur <= ennemiY + ennemiLongueur);

        if (linkAGauche && linkMemeLigne) {
            bouleDeFeu = new BouleDeFeu(ennemiX, ennemiY, this.environnement, 30, 32, 32);
            bouleDeFeu.setDirection("LEFT");
            this.environnement.ajouterBouleDeFeu(bouleDeFeu);
        }
    }

}
