package com.example.sae_zeldalike.Controlleur.MenuJeu;

public class Commande {

    private final String touche;
    private final String action;

    public Commande(String touche, String action) {
        this.touche = touche;
        this.action = action;
    }

    public String getTouche() {
        return touche;
    }

    public String getAction() {
        return action;
    }

}
