package com.example.sae_zeldalike.modele.Projectile;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arc;

public class Fleche extends Projectile {

    private Arc arc;

    public Fleche(Environnement environnement, int positionX, int positionY, int largeur, int longueur, int vitesse,Arc arc) {
        super(environnement, positionX, positionY, largeur, longueur, vitesse,0);
        this.arc=arc;
        this.setDegats(arc.getDégâts());
    }

        public boolean depasseRayon(){
        int rayonAttaque = this.arc.getRayonAttaque();
        if(this.getPositionX() < this.getPositionX() - rayonAttaque || this.getPositionX() > this.getPositionX() + rayonAttaque ||
                this.getPositionY() < this.getPositionY() - rayonAttaque || this.getPositionY() > this.getPositionY() + rayonAttaque){
            return true;
        }
        return false;
    }
}
