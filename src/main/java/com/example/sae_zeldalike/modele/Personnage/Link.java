package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Link extends Personnage {

    private IntegerProperty portefeuille;

    public Link(Environnement environnement, int positionX, int positionY) {

        super(100, 0, environnement, positionX, positionY, 5, 32,32);
        this.portefeuille= new SimpleIntegerProperty();

    }

    public void essaiRamasserPiece(){

        System.out.println(environnement.getItems());

        for(Item item : this.environnement.getItems()){

            if(item instanceof Piece){


                if((this.getPositionX()-30<= item.getPositionY() && item.getPositionY()<=this.getPositionY()+30) && (this.getPositionX()-30<= item.getPositionX() && item.getPositionX()<=this.getPositionX()+30)){

                    ajouterPiece(((Piece) item).getValeur());
                    System.out.println("valeur piece "+ ((Piece) item).getValeur());
                    getEnvironnement().getItems().remove(item);
                }
            }
        }
        System.out.println("Rammasage à perte");
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
        portefeuille.add(piece);
    }

}
