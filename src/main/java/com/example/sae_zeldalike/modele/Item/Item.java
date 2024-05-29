package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Hitbox;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class Item {

    private Environnement environnement;
    private static int compteurItem=0;
    private String id;
    private IntegerProperty positionX;
    private IntegerProperty positionY;
    private final int largeur;
    private final int longueur;

    public Item(Environnement environnement, int positionX,int positionY ){
        this.environnement=environnement;
        this.positionX=new SimpleIntegerProperty(positionX);
        this.positionY=new SimpleIntegerProperty(positionY);
        this.id="I"+compteurItem;
        compteurItem++;
        this.largeur=16;
        this.longueur=16;
        environnement.ajouterItem(this);

    }
    public Item(Environnement environnement){
        this.environnement=environnement;
        this.id="I"+compteurItem;
        compteurItem++;
        this.largeur=16;
        this.longueur=16;
        this.positionX=new SimpleIntegerProperty();
        this.positionY=new SimpleIntegerProperty();
        genererPositionAleatoires();
        environnement.ajouterItem(this);

    }
    public Hitbox hitbox(int x, int y){

        Hitbox hitbox = new Hitbox(x,y,getLargeur(),getLongueur());
        return hitbox;
    }

    public void genererPositionAleatoires(){
        Random random = new Random();
        int posX,posY;
        do{
            posX = (random.nextInt((getEnvironnement().getMap().getColonne()-1)*getEnvironnement().getMap().getTailleTuile()));
            posY = (random.nextInt((getEnvironnement().getMap().getLigne()-1)*getEnvironnement().getMap().getTailleTuile()));

//            System.out.println("posX="+posX+",posY="+posY);
        }while (!getEnvironnement().estDansTuile(11,hitbox(posX,posY)));
        setPositionX(posX);
        setPositionY(posY);
    }
    public Environnement getEnvironnement() {
        return environnement;
    }

    public int getPositionX() {
        return positionX.getValue();
    }

    public IntegerProperty getPositionXProperty() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX.setValue(positionX);
    }

    public int getPositionY() {
        return positionY.getValue();
    }

    public IntegerProperty getPositionYProperty() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY.setValue(positionY);
    }

    public int getLargeur(){return largeur;}

    public int getLongueur(){return longueur;}

    public String getId() {
        return id;
    }

}
