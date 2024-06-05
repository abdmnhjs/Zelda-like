package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import com.example.sae_zeldalike.modele.Item.Stockable;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Link extends Personnage {

    private IntegerProperty portefeuille;
    private ObjectProperty<Stockable>[] inventaire;
    private IntegerProperty numeroCaseActuel;

    public Link(Environnement environnement, int positionX, int positionY) {

        super(100, 0, environnement, positionX, positionY, 7, 32,32);
        this.portefeuille= new SimpleIntegerProperty();
        this.inventaire=new SimpleObjectProperty[3];
        for (int i=0;i<inventaire.length;i++) {
            inventaire[i] = new SimpleObjectProperty();
        }
        this.numeroCaseActuel=new SimpleIntegerProperty(0);

    }

    public Link(Environnement environnement) {

        super(100, 0, environnement, 7, 32,32);
        this.portefeuille= new SimpleIntegerProperty();
        this.inventaire=new SimpleObjectProperty[3];
        for (int i=0;i<inventaire.length;i++) {
            inventaire[i] = new SimpleObjectProperty();
        }
        this.numeroCaseActuel=new SimpleIntegerProperty(0);

    }

    public int longueurTableau(){
        return inventaire.length;
    }

    public ObjectProperty<Stockable>[] getInventaire() {
        return inventaire;
    }

    public void utiliserItemDansInventaire(){
        if (inventaire[getNumeroCaseActuel()].getValue()!=null) {
            Stockable item = inventaire[getNumeroCaseActuel()].getValue();
            item.getItem().setPositionX(getPositionX()-(getLargeur()/4));
            item.getItem().setPositionY(getPositionY()-(getLongueur()/4));
            inventaire[getNumeroCaseActuel()].setValue(null);
            getEnvironnement().ajouterItem(item.getItem());
                Timer timer = new Timer();

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            item.utiliserCapacite();
                            timer.cancel();
                        });
                    }
                };
                timer.schedule(task, 1900);


        }

    }

    public boolean emplacementInventaireLibre(){

        for (ObjectProperty<Stockable>emplacement : inventaire) {
            if (emplacement.get() == null) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> connaitreIndiceCaseVide(){

        ArrayList<Integer>indice = new ArrayList<>();
        for (int i=0;i<inventaire.length;i++) {
            if (inventaire[i].get() == null) {
                indice.add(i);
            }
        }
        return indice;
    }

    public void ajouteItemDansInventaire(Stockable item) {

        if (emplacementInventaireLibre()) {
            modifieCaseInventaire(item);
        }
    }

    public void modifieCaseInventaire(Stockable item) {
        if (inventaire[getNumeroCaseActuel()].getValue()==null){
            inventaire[getNumeroCaseActuel()].setValue(item);
        }else{
            ArrayList<Integer> indice = connaitreIndiceCaseVide();
            inventaire[indice.getFirst().intValue()].setValue(item);
        }
    }

    public IntegerProperty getNumeroCaseActuelProperty(){
        return numeroCaseActuel;
    }

    public int getNumeroCaseActuel() {
        return numeroCaseActuel.getValue();
    }

    public void setNumeroCaseActuel(int numero) {
        if (numero>=0 && numero< inventaire.length) {
            this.numeroCaseActuel.setValue(numero);
        }
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
