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
        ArrayList<Personnage>dead = new ArrayList<>();

        for (Personnage perso : getEnvironnement().getPersonnages()) {

            if (!(perso instanceof Link)) {

                switch (getDirection()) {

                    case "UP" -> {
                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
                            perso.reduirePointsDeVie(getDégâts());
                        }


                    }
                    case "DOWN" -> {
                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
                            perso.reduirePointsDeVie(getDégâts());
                        }
                        System.out.println("Attaque en B");
                    }
                    case "LEFT" -> {
                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
                            perso.reduirePointsDeVie(getDégâts());
                        }
                        System.out.println("Attaque a G");
                    }
                    case "RIGHT" -> {
                        if (this.getEnvironnement().estDansLaZone(this.hitbox(this.getPositionX(),this.getPositionY()),perso.hitbox(perso.getPositionX(), perso.getPositionY()))) {
                            perso.reduirePointsDeVie(getDégâts());
                        }
                        System.out.println("Attaque a D");
                    }
                }
            }
            if (!perso.estVivant()){
                dead.add(perso);
            }
        }
        System.out.println(dead.size());
        for (Personnage perso : dead) {
            perso.getEnvironnement().supprimerPersonnage(perso);
        }
    }
}
