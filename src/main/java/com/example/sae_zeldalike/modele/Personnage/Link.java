package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.Piece;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Link extends Personnage {

    private ObservableList<Piece> pieces;

    public Link(Environnement environnement, int positionX, int positionY) {
        super(100, 0, environnement, positionX, positionY, 5);

        this.pieces= FXCollections.observableArrayList();

    }

    /*public Link(Environnement environnement) {

        super(100, 0, environnement, 5);
        this.pieces=FXCollections.observableArrayList();
    }*/
    public void essaiRamasserPiece(){

        System.out.println(environnement.getItems());
        for(Item item : this.environnement.getItems()){
            if(item instanceof Piece){
                System.out.println("posItem "+item.getPositionX()+"\n"+item.getPositionY());
                System.out.println("posJ "+getPositionX()+"\n"+getPositionY());
                if(		(this.getPositionX()-30<= item.getPositionY() && item.getPositionY()<=this.getPositionY()+30) &&
                        (this.getPositionX()-30<= item.getPositionX() && item.getPositionX()<=this.getPositionX()+30)
                ){
                    ajouterPiece((Piece) item);
                    System.out.println("Piece ajouter");
                    System.out.println(getPieces());
                }
            }
            System.out.println("Rammasage à perte");
        }

    }

    public ObservableList getPieces(){
        return pieces;
    }

    public void ajouterPiece(Piece piece) {
        pieces.add(piece);
    }

}
