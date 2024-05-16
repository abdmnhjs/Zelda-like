package com.example.sae_zeldalike.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private String id;
    private static int compteurEnvironnement=0;
    private ObservableList<Personnage> personnages;
    private int[][] cartes;
    private int[] carte;

    public Environnement(){

        this.id = "E"+compteurEnvironnement;
        compteurEnvironnement++;
        this.personnages= FXCollections.observableArrayList();
    }

    public void ajouterPersonnage(Personnage personnage){
        personnages.add(personnage);
    }

    public ObservableList<Personnage> getPersonnages() {
        return personnages;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Environnement{" +
                "id='" + id + '\'' +
                ", personnages=" + personnages +
                '}';
    }
}
