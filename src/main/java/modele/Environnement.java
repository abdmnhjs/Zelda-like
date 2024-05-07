package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Environnement {

    private String id;
    private static int compteurEnvironnement=0;
    private ObservableList<Personnage> personnages;
    private int width;
    private int height;
    private int[][] tableau;

    public Environnement(int width,int height){

        this.id = "E"+compteurEnvironnement;
        compteurEnvironnement++;
        this.width = width;
        this.height = height;
        this.tableau = new int[this.width][this.height];
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
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean estDansTerrain(int x, int y){
        return (0 <= x && x<this.width && 0<=y && y< this.height);
    }

    @Override
    public String toString() {
        return "Environnement{" +
                "id='" + id + '\'' +
                ", personnages=" + personnages +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
