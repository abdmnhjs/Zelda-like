package com.example.sae_zeldalike.modele;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Projectile;
import com.example.sae_zeldalike.modele.Personnage.Ennemi;

public class BouleDeFeu extends Projectile {
    private static int compteurBouleDeFeu;
    public BouleDeFeu(int x, int y, Environnement environnement, int vitesse, int longueur, int largeur) {
        super(x, y, environnement, vitesse, longueur, largeur);
        this.id = "B"+compteurBouleDeFeu;
        compteurBouleDeFeu++;
    }
}
