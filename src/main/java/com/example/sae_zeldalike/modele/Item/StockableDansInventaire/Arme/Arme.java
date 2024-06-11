package com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Stockable;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public abstract class Arme extends Item implements Stockable {

    private int dégâts;
    private int rayonAttaque;
    protected StringProperty direction;
    protected int initialX;
    protected int initialY;
    private Personnage personnage;
    private boolean porterParPerso;

    public Arme(Environnement environnement, int positionX, int positionY, int largeur, int longueur,int degats,int rayonAttaque) {
        super(environnement, positionX, positionY, largeur, longueur);
        this.dégâts=degats;
        this.rayonAttaque=rayonAttaque;
        this.direction=new SimpleStringProperty("UP");
        this.initialX=positionX;
        this.initialY=positionY;
        this.personnage=null;
        porterParPerso=false;

    }

    public Arme(Environnement environnement, int largeur, int longueur,int degats,int rayonAttaque) {
        super(environnement, largeur, longueur);
        this.dégâts=degats;
        this.rayonAttaque=rayonAttaque;
        this.direction=new SimpleStringProperty("UP");
        this.initialX=getPositionX();
        this.initialY=getPositionY();
        this.personnage=null;
        porterParPerso=false;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public void setPersonnage(Personnage personnage) {
        if (!estPorterParPerso() && personnage!=null) {
            this.personnage = personnage;
            setPorterParPerso(true);
        }else {
            setPorterParPerso(false);
            this.personnage=null;
        }
    }

    public boolean estPorterParPerso() {
        return porterParPerso;
    }

    public void setPorterParPerso(boolean porterParPerso) {
        this.porterParPerso = porterParPerso;
    }

    public void faireDégâts(Personnage personnage, int dégâts){
        ArrayList<Personnage> dead = new ArrayList<>();
        if(dégâts <= personnage.getPointVie() && dégâts > 0){
            personnage.setPointVie(personnage.getPointVie() - dégâts);
            if (!personnage.estVivant()){
                dead.add(personnage);
            }

        }
        for (Personnage perso : dead) {
            perso.getEnvironnement().supprimerPersonnage(perso);
        }
    }

    public boolean estSurEnnemi(Personnage ennemi){
        if(super.getPositionX() < ennemi.getPositionX() + ennemi.getLargeur() &&
                this.getPositionX() + this.getLargeur() > ennemi.getPositionX() &&
                this.getPositionX() < ennemi.getPositionY() + ennemi.getLongueur() &&
                this.getPositionX() + this.getLongueur() > ennemi.getPositionY()){
            return true;
        }
        return false;
    }

    public String getDirection() {
        return this.direction.getValue();
    }

    public void setDirection(String direction) {
        this.direction.setValue(direction);
    }

    public StringProperty getDirectionProperty() {
        return this.direction;
    }

    public int getInitialX() {
        return this.initialX;
    }

    public int getInitialY() {
        return this.initialY;
    }

    public int getDégâts() {
        return this.dégâts;
    }

    public int getRayonAttaque() {
        return this.rayonAttaque;
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public boolean effetUtiliser() {
        return false;
    }

    @Override
    public  void utiliserCapacite(){
        for (Personnage personnage : getEnvironnement().getPersonnages()) {

            if (!(personnage instanceof Link)) {

                switch (getDirection()) {

                    case "UP" -> {
                        if (this.getPositionY() - getRayonAttaque() <= personnage.getPositionY()) {
                            personnage.reduirePointsDeVie(getDégâts());
                        }
                        System.out.println("Attaque en H");
                    }
                    case "DOWN" -> {
                        if (this.getPositionY() + getRayonAttaque() <= personnage.getPositionY()) {
                            personnage.reduirePointsDeVie(getDégâts());
                        }
                        System.out.println("Attaque en B");
                    }
                    case "LEFT" -> {
                        if (this.getPositionX() - getRayonAttaque() <= personnage.getPositionX()) {
                            personnage.reduirePointsDeVie(getDégâts());
                        }
                        System.out.println("Attaque a G");
                    }
                    case "RIGHT" -> {
                        if (this.getPositionX() + getRayonAttaque() <= personnage.getPositionX()) {
                            personnage.reduirePointsDeVie(getDégâts());
                        }
                        System.out.println("Attaque a D");
                    }
                }
            }
        }
    }
}
