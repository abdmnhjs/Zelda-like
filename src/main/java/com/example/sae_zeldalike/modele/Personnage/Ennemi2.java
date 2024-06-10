package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.BouleDeFeu;
import com.example.sae_zeldalike.modele.Environnement.Environnement;

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
        int ennemiWidth = this.getLargeur();
        int ennemiHeight = this.getLongueur();

        int linkX = link.getPositionX();
        int linkY = link.getPositionY();
        int linkWidth = link.getLargeur();
        int linkHeight = link.getLongueur();

        // Vérifie si Link est au-dessus de l'ennemi et dans la même colonne (en prenant en compte les largeurs et hauteurs)
        boolean isLinkAbove = linkY + linkHeight < ennemiY;
        boolean isLinkInSameColumn = (linkX >= ennemiX && linkX <= ennemiX + ennemiWidth) || (linkX + linkWidth >= ennemiX && linkX + linkWidth <= ennemiX + ennemiWidth);

        if(isLinkAbove && isLinkInSameColumn) {
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
        int ennemiWidth = this.getLargeur();
        int ennemiHeight = this.getLongueur();

        int linkX = link.getPositionX();
        int linkY = link.getPositionY();
        int linkWidth = link.getLargeur();
        int linkHeight = link.getLongueur();

        // Vérifie si Link est en-dessous de l'ennemi et dans la même colonne (en prenant en compte les largeurs et hauteurs)
        boolean isLinkBelow = linkY > ennemiY + ennemiHeight;
        boolean isLinkInSameColumn = (linkX >= ennemiX && linkX <= ennemiX + ennemiWidth) || (linkX + linkWidth >= ennemiX && linkX + linkWidth <= ennemiX + ennemiWidth);

        if (isLinkBelow && isLinkInSameColumn) {
            bouleDeFeu = new BouleDeFeu(ennemiX, ennemiY, this.environnement, 30, 32, 32);
            bouleDeFeu.setDirection("DOWN");
            this.environnement.ajouterBouleDeFeu(bouleDeFeu);
        }
    }


    public void tirerProjectileDroite(Link link) {
        BouleDeFeu bouleDeFeu;

        int ennemiX = this.getPositionX();
        int ennemiY = this.getPositionY();
        int ennemiWidth = this.getLargeur();
        int ennemiHeight = this.getLongueur();

        int linkX = link.getPositionX();
        int linkY = link.getPositionY();
        int linkWidth = link.getLargeur();
        int linkHeight = link.getLongueur();

        // Vérifie si Link est à droite de l'ennemi et dans la même ligne (en prenant en compte les longueurs et hauteurs)
        boolean isLinkToTheRight = linkX > ennemiX + ennemiWidth;
        boolean isLinkInSameRow = (linkY >= ennemiY && linkY <= ennemiY + ennemiHeight) || (linkY + linkHeight >= ennemiY && linkY + linkHeight <= ennemiY + ennemiHeight);

        if (isLinkToTheRight && isLinkInSameRow) {
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
        int ennemiHeight = this.getLongueur();

        int linkX = link.getPositionX();
        int linkY = link.getPositionY();
        int linkWidth = link.getLargeur();
        int linkHeight = link.getLongueur();

        // Vérifie si Link est à gauche de l'ennemi et dans la même ligne (en prenant en compte les longueurs et hauteurs)
        boolean isLinkToTheLeft = linkX + linkWidth < ennemiX;
        boolean isLinkInSameRow = (linkY >= ennemiY && linkY <= ennemiY + ennemiHeight) || (linkY + linkHeight >= ennemiY && linkY + linkHeight <= ennemiY + ennemiHeight);

        if (isLinkToTheLeft && isLinkInSameRow) {
            bouleDeFeu = new BouleDeFeu(ennemiX, ennemiY, this.environnement, 30, 32, 32);
            bouleDeFeu.setDirection("LEFT");
            this.environnement.ajouterBouleDeFeu(bouleDeFeu);
        }
    }

}
