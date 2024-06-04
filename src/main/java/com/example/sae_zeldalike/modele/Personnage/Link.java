package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Bombe;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import com.example.sae_zeldalike.modele.Item.Stockable;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Link extends Personnage {

    private IntegerProperty portefeuille;
    private ObservableList<Stockable> inventaire;
    private int numeroCaseActuel;

    public Link(Environnement environnement, int positionX, int positionY) {

        super(100, 0, environnement, positionX, positionY, 5, 32,32);
        this.portefeuille= new SimpleIntegerProperty();
        this.inventaire = FXCollections.observableArrayList(new Bombe(environnement),new Bombe(environnement),null);
        this.numeroCaseActuel=0;

    }

    public Link(Environnement environnement) {

        super(100, 0, environnement, 5, 32,32);
        this.portefeuille= new SimpleIntegerProperty();
        this.inventaire = FXCollections.observableArrayList();
        this.numeroCaseActuel=0;

    }

    public ObservableList<Stockable> getInventaire() {
        return inventaire;
    }

    public void utiliserItemDansInventaire(){
        if (!inventaire.isEmpty()){
            Stockable item = inventaire.get(0);
            item.getItem().setPositionX(getPositionX()-(getLargeur()/4));
            item.getItem().setPositionY(getPositionY()-(getLongueur()/4));
            inventaire.remove(item);
            getEnvironnement().ajouterItem(item.getItem());
            if (item instanceof Bombe){
                Timer timer = new Timer();

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            ((Bombe) item).explose();
                            timer.cancel();
                        });
                    }
                };
                timer.schedule(task, 1900);


            }
        }
    }

    public boolean emplacementInventaireLibre(){

        return inventaire.size()<8;
    }

    public void ajouteItemDansInventaire(Stockable item) {

        if (emplacementInventaireLibre()&& numeroCaseActuel<3) {
            inventaire.set(numeroCaseActuel,item);
            setNumeroCaseActuel(getNumeroCaseActuel()+1);
        }
    }

    public int getNumeroCaseActuel() {
        return numeroCaseActuel;
    }

    public void setNumeroCaseActuel(int numeroCaseActuel) {
        this.numeroCaseActuel = numeroCaseActuel;
    }

    public Item essaiRamasserItem() {

        for (Item item : this.environnement.getItems()) {

            if ((this.getPositionY() - 20 <= item.getPositionY() && item.getPositionY() <= this.getPositionY() + 20)
                    && (this.getPositionX() - 20 <= item.getPositionX() && item.getPositionX() <= this.getPositionX() + 20)) {
                if (item instanceof Piece) {
                    return item;
                }
                if (item instanceof Stockable) {
                    return item;
                }
            }
        }

        return null;
    }


    public int getPortefeuille(){
        return portefeuille.getValue();  }
    public IntegerProperty getPortefeuilleProperty(){
        return portefeuille;
    }
    public void setPortefeuilleProperty(int portefeuille){
        this.portefeuille.setValue(portefeuille);
    }

    public void ajouterPiece(int piece) {
        portefeuille.setValue(portefeuille.getValue()+piece);
    }

}
