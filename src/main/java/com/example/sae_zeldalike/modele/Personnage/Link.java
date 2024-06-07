package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Inventaire;
import com.example.sae_zeldalike.modele.Item.Bombe;
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
    private Inventaire inventaire;
    private IntegerProperty numeroCaseActuel;

    public Link(Environnement environnement, int positionX, int positionY) {

        super(100, 0, environnement, positionX, positionY, 7, 32,32);
        this.portefeuille= new SimpleIntegerProperty();
        this.inventaire=new Inventaire();
        this.numeroCaseActuel=new SimpleIntegerProperty(0);

    }

    public Link(Environnement environnement) {

        super(100, 0, environnement, 7, 32,32);
        this.portefeuille= new SimpleIntegerProperty();
        this.inventaire=new Inventaire();
        this.numeroCaseActuel=new SimpleIntegerProperty(0);

    }


    public Inventaire getInventaire(){
        return inventaire;
    }
    public void utiliserItemDansInventaire(){

        if (getInventaire().getInventaireCaseActuel()!=null) {

            Stockable item = getInventaire().getInventaireCaseActuel();
            item.getItem().setPositionX(getPositionX()-(getLargeur()/4));
            item.getItem().setPositionY(getPositionY()-(getLongueur()/4));

            getInventaire().modifieStockableInventaire(null);
            getEnvironnement().ajouterItem(item.getItem());
            getInventaire().setIndiceChangement(1);


//            item.utiliserCapacite();
//            getInventaire().setIndiceChangement(-1);
            ((Bombe) item).setEffetUtiliser(true);

                Timer timer = new Timer();

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {

                            item.utiliserCapacite();
                            getInventaire().setIndiceChangement(-1);

                            timer.cancel();
                        });
                    }
                };
                timer.schedule(task, 1900);


        }

    }

    public boolean emplacementInventaireLibre(){

        for (Stockable emplacement : getInventaire().getInventaire()) {
            if (emplacement == null) {
                return true;
            }
        }
        return false;
    }

    public void ajouteItemDansInventaire(Stockable item) {
        if (emplacementInventaireLibre()) {

            modifieCaseInventaire(item);
            getInventaire().setIndiceChangement(1);
        }
        getInventaire().setIndiceChangement(-1);
    }

    public void modifieCaseInventaire(Stockable item) {

        if (getInventaire().getInventaireCaseActuel()==null){
            getInventaire().modifieStockableInventaire(item);
        }

        else{
            ArrayList<Integer> indice = getInventaire().connaitreIndiceCaseVide();
            if (indice.size()!=0){
                getInventaire().modifieCaseInventaireAvecIndiceDonnee(indice.get(0),item);
            }
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
