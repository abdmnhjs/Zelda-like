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
        super(100, 0, environnement, positionX, positionY, 5);

        this.portefeuille= new SimpleIntegerProperty();

    }

    /*public Link(Environnement environnement) {

        super(100, 0, environnement, 5);
        this.pieces=FXCollections.observableArrayList();
    }*/
    public void essaiRamasserPiece(){

        System.out.println(environnement.getItems());
        for(Item item : this.environnement.getItems()){
            if(item instanceof Piece){
                System.out.println("posItem "+item.getPositionX()+"  "+item.getPositionY());
                System.out.println("posJ "+getPositionX()+"  "+getPositionY());
                if((this.getPositionX()-30<= item.getPositionY() && item.getPositionY()<=this.getPositionY()+30)
                    && (this.getPositionX()-30<= item.getPositionX() && item.getPositionX()<=this.getPositionX()+30)){
                    ajouterPiece(((Piece) item).getValeur());
                    System.out.println("Piece ajoutée ");
                    System.out.println("valeur piece "+ ((Piece) item).getValeur());
                    this.getEnvironnement().getItem(item.getId());
                }
            }
            System.out.println("Rammasage à perte");
        }

    }

    public int getPortefeuille(){
        return portefeuille.getValue();  }

    public void ajouterPiece(int piece) {
        portefeuille.add(piece);
    }

}
