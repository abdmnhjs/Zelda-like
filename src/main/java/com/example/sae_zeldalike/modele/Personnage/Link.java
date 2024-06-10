package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Inventaire;
import com.example.sae_zeldalike.modele.Item.*;
import com.example.sae_zeldalike.modele.Item.NonStockable.Effet;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.*;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.*;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.*;

public class Link extends Personnage {

    private IntegerProperty portefeuille;
    private Inventaire inventaire;
    private IntegerProperty numeroCaseActuel;
    private IntegerProperty pointDeVieAdditionelle;
    private final IntegerProperty pointDeVieMax;
    private ArrayList<Effet> effets;
    private Arme armeEquiper;

    public Link(Environnement environnement, int positionX, int positionY) {

        super(100, 0, environnement, positionX, positionY, 7, 32,32);
        this.portefeuille= new SimpleIntegerProperty();
        this.inventaire=new Inventaire();
        this.numeroCaseActuel=new SimpleIntegerProperty(0);
        this.pointDeVieAdditionelle = new SimpleIntegerProperty(0);
        this.pointDeVieMax = new SimpleIntegerProperty(getPointDeVieAdditionelle()+getPointVie());
        armeEquiper=null;

    }

    public Link(Environnement environnement) {

        super(100, 0, environnement, 7, 32,32);
        this.portefeuille= new SimpleIntegerProperty();
        this.inventaire=new Inventaire();
        this.numeroCaseActuel=new SimpleIntegerProperty(0);
        this.pointDeVieAdditionelle = new SimpleIntegerProperty(0);
        this.pointDeVieMax = new SimpleIntegerProperty(getPointDeVieAdditionelle()+getPointVie());
        armeEquiper=null;
    }

    public Arme getArmeEquiper() {
        return armeEquiper;
    }

    public void setArmeEquiper(Arme armeEquiper) {

        this.armeEquiper = armeEquiper;
    }

    public void ajouterBouclier(int ajout) {
        pointDeVieAdditionelle.set(pointDeVieAdditionelle.get() + ajout);
    }

    public void setPointDeVieAdditionelle(int pointDeVieAdditionnelle) {
        this.pointDeVieAdditionelle.setValue(pointDeVieAdditionnelle);
    }

    public int getPointDeVieAdditionelle() {
        return pointDeVieAdditionelle.getValue();
    }

    public IntegerProperty getPointDeVieAdditionelleProperty() {
        return pointDeVieAdditionelle;
    }

    public int getPointDeVieMax() {
        return pointDeVieMax.getValue();
    }

    public IntegerProperty pointDeVieMaxProperty() {
        return pointDeVieMax;
    }

    public void reduirePointsDeVie(int dommages) {
        int bouclierRestant = pointDeVieAdditionelle.get() - dommages;
        if (bouclierRestant >= 0) {
            pointDeVieAdditionelle.set(bouclierRestant);
        } else {
            pointDeVieAdditionelle.set(0);
            int pvRestant = pointVie.get() + bouclierRestant;
            if (pvRestant<0){
                pvRestant = 0;
            }
            pointVie.set(pvRestant);
        }
    }

    public Arc getArc(){
        for(Stockable arme : this.inventaire.getInventaire()){
            if(arme instanceof Arc){
                return (Arc) arme;
            }
        }
        return null;
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



                            timer.cancel();
                        });
                    }
                };
                timer.schedule(task, 1900);

            getInventaire().setIndiceChangement(-1);

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

                if (!(item instanceof Stockable)){
                    return item;
                }else if (item instanceof Stockable && !((Stockable) item).effetUtiliser()){
                    return item;
                }
//                if (item instanceof Piece) {
//                    return item;
//                }if (item instanceof CoeurRouge){
//                    return item;
//                }
//                if (item instanceof CoeurBleu){
//                    return item;
//                }
//                if (item instanceof Stockable) {
//                    if (item instanceof Bombe && !((Bombe) item).isEffetUtiliser())
//                    return item;
//                }if (item instanceof SuperMegaFast) {
//                    return item;
//                }

            }
        }

        return null;
    }

    public ArrayList<Effet> getEffets() {
        return effets;
    }

    public void ajouterEffet(Effet effet){
        effets.add(effet);
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

    public void attaquer(){
        if (armeEquiper!=null){
            if (getArmeEquiper() instanceof Arc){

            }
        }
    }

}
