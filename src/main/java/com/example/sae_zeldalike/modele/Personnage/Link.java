package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import com.example.sae_zeldalike.modele.Item.Stockable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Link extends Personnage {

    private IntegerProperty portefeuille;
    private Stockable[] inventaire;

    public Link(Environnement environnement, int positionX, int positionY) {

        super(100, 0, environnement, positionX, positionY, 5, 32,32);
        this.portefeuille= new SimpleIntegerProperty();
        inventaire = new Stockable[3];

    }

    public Stockable[] getInventaire() {
        return inventaire;
    }



    public Item essaiRamasserItem(){

        for(Item item : this.environnement.getItems()){
            if(item instanceof Piece){
//                System.out.println("X= "+item.getPositionX()+" Y= "+item.getPositionY());
                if((this.getPositionY()-20<= item.getPositionY() && item.getPositionY()<=this.getPositionY()+20)
                && (this.getPositionX()-20<= item.getPositionX() && item.getPositionX()<=this.getPositionX()+20)){
//                    System.out.println("Piece récupéré");
                    return item;
                }
            }

        }
//        System.out.println("Rien ramassé");
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
