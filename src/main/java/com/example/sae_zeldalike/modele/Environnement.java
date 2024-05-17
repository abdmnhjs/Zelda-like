package com.example.sae_zeldalike.modele;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
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
=======
    private Map map;

    public Environnement(Map map){

        this.id = "E"+compteurEnvironnement;
        compteurEnvironnement++;
        this.map=map;
>>>>>>> origin/environnement
        this.personnages= FXCollections.observableArrayList();
    }

    public void ajouterPersonnage(Personnage personnage){
        personnages.add(personnage);
    }

    public ObservableList<Personnage> getPersonnages() {
        return personnages;
    }

    public Map getMap() {
        return map;
    }


    public String getId() {
        return id;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return "Environnement{" +
                "id='" + id + '\'' +
                ", personnages=" + personnages +
                '}';
    }
=======

>>>>>>> origin/environnement
}
