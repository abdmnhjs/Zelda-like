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
    private IntegerProperty pointDeVieAdditionelle;
    private final IntegerProperty pointDeVieMax;
    protected ArrayList<Effet> effets;
    private Arme armeEquiper;

    public Link(Environnement environnement, int positionX, int positionY) {

        super(100, 0, environnement, positionX, positionY, 7, 32,32);
        this.portefeuille= new SimpleIntegerProperty();
        this.inventaire=new Inventaire();
        this.pointDeVieAdditionelle = new SimpleIntegerProperty(0);
        this.pointDeVieMax = new SimpleIntegerProperty(getPointDeVieAdditionelle()+getPointVie());
        effets = new ArrayList<>();
        armeEquiper=null;

    }

    public Link(Environnement environnement) {

        super(100, 0, environnement, 7, 32,32);
        this.portefeuille= new SimpleIntegerProperty();
        this.inventaire=new Inventaire();
        this.pointDeVieAdditionelle = new SimpleIntegerProperty(0);
        this.pointDeVieMax = new SimpleIntegerProperty(getPointDeVieAdditionelle()+getPointVie());
        effets = new ArrayList<>();
        armeEquiper=null;
    }

    public Arme getArmeEquiper() {
        return armeEquiper;
    }

    public void setArmeEquiper(Arme arme) {
        desequiperArme();
        if (arme!=null) {
            this.armeEquiper = arme;
            getEnvironnement().ajouterItem(getArmeEquiper());
            getArmeEquiper().setPersonnage(this);

        }
    }

    private void desequiperArme(){
        if (armeEquiper !=null){
            getEnvironnement().supprimerItem(getArmeEquiper());
            getArmeEquiper().setPersonnage(null);
            armeEquiper = null;
        }
    }

    public void ajouterBouclier(int ajout) {
        pointDeVieAdditionelle.set(pointDeVieAdditionelle.get() + ajout);
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

    public void reduirePointsDeVie(int degats) {
        int bouclierRestant = pointDeVieAdditionelle.get() - degats;
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

    public Inventaire getInventaire(){
        return inventaire;
    }
    public void utiliserItemDansInventaire(){

        if (getInventaire().getInventaireCaseActuel()!=null) {

            if (!(inventaire.getInventaireCaseActuel() instanceof Arme)) {

                Stockable item = getInventaire().getInventaireCaseActuel();
                item.getItem().setPositionX(getPositionX() - (getLargeur() / 4));
                item.getItem().setPositionY(getPositionY() - (getLongueur() / 4));

                getInventaire().modifieStockableInventaire(null);
                getEnvironnement().ajouterItem(item.getItem());
                getInventaire().setIndiceChangement(1);

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

            }else {
                if (getInventaire().getInventaireCaseActuel() instanceof  Arme ) {
                        Arme arme = (Arme) getInventaire().getInventaireCaseActuel();
                        setArmeEquiper(arme);
                }
            }
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

    public Item essaiRamasserItem(){

        for (Item item : this.environnement.getItems()) {

                if (getEnvironnement().estDansLaZone(this.hitbox(getPositionX(),getPositionY()),item.hitbox(item.getPositionX(),item.getPositionY()))) {
                    if (!(item instanceof Stockable)) {
                        return item;
                    } else if ((item instanceof Stockable && !((Stockable) item).effetUtiliser()) && !(item instanceof Arme)) {
                        return item;
                    } else if ((item instanceof Arme && ((Arme) item).getPersonnage() == null && !((Arme) item).estPorterParPerso())) {
                        return item;
                    }
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

    public IntegerProperty getPortefeuilleProperty(){
        return portefeuille;
    }

    public void ajouterPiece(int piece) {
        portefeuille.setValue(portefeuille.getValue()+piece);
    }

}
