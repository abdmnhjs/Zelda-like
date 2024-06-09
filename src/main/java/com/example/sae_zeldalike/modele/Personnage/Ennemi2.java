package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.BouleDeFeu;
import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class Ennemi2 extends Ennemi{
    private static int compteurEnnemi2 = 0;
    public Ennemi2(Environnement environnement){
        super(200, 40, environnement, 5, 32, 32);
        this.probaAttaque = 80;
    }

    public void tirerBouleDeFeu(){
        if(proba(probaAttaque)){
            tirerProjectileHaut();
            tirerProjectileBas();
            tirerProjectileDroite();
            tirerProjectileGauche();
        }
    }

    public void tirerProjectileHaut(){
        BouleDeFeu bouleDeFeu;
        int y = this.getPositionY();
        int linkY = this.environnement.getLinkRemovalQueue().get(0).getPositionY();
        while (y != linkY){
            y--;
            if(y == linkY){
                bouleDeFeu = new BouleDeFeu(this.getPositionX(), this.getPositionY(), this.environnement, 30, 32, 32);
                bouleDeFeu.seDeplacerHaut();
            } else if (this.environnement.estDansLimiteTerrain(this.getPositionX(), y, this.getLongueur(), this.getLargeur())){
                break;
            }
        }
    }

    public void tirerProjectileBas(){
        BouleDeFeu bouleDeFeu;
        int y = this.getPositionY();
        int linkY = this.environnement.getLinkRemovalQueue().get(0).getPositionY();
        while (y != linkY){
            y++;
            if(y == linkY){
                bouleDeFeu = new BouleDeFeu(this.getPositionX(), this.getPositionY(), this.environnement, 30, 32, 32);
                bouleDeFeu.seDeplacerBas();
            } else if (this.environnement.estDansLimiteTerrain(this.getPositionX(), y, this.getLongueur(), this.getLargeur())){
                break;
            }
        }
    }

    public void tirerProjectileDroite(){
        BouleDeFeu bouleDeFeu;
        int x = this.getPositionX();
        int linkX = this.environnement.getLinkRemovalQueue().get(0).getPositionX();
        while (x != linkX){
            x++;
            if(x == linkX){
                bouleDeFeu = new BouleDeFeu(this.getPositionX(), this.getPositionY(), this.environnement, 30, 32, 32);
                bouleDeFeu.seDeplacerDroite();

            } else if (this.environnement.estDansLimiteTerrain(x, this.getPositionY(), this.getLongueur(), this.getLargeur())){
                break;
            }
        }
    }

    public void tirerProjectileGauche(){
        BouleDeFeu bouleDeFeu;
        int x = this.getPositionX();
        int linkX = this.environnement.getLinkRemovalQueue().get(0).getPositionX();
        while (x != linkX){
            x--;
            if(x == linkX){
                bouleDeFeu = new BouleDeFeu(this.getPositionX(), this.getPositionY(), this.environnement, 30, 32, 32);
                bouleDeFeu.seDeplacerDroite();
            } else if (this.environnement.estDansLimiteTerrain(x, this.getPositionY(), this.getLongueur(), this.getLargeur())){
                break;
            }
        }
    }
}
