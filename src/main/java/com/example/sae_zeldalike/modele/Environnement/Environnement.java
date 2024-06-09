package com.example.sae_zeldalike.modele.Environnement;

import com.example.sae_zeldalike.modele.Hitbox;
import com.example.sae_zeldalike.modele.Item.*;
import com.example.sae_zeldalike.modele.Personnage.Ennemi1;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private String id;
    private static int compteurEnvironnement=0;
    private ObservableList<Personnage> personnages;
    private ObservableList<Item> items;
    private Map map;

    public Environnement(Map map){

        this.id = "E"+compteurEnvironnement;
        compteurEnvironnement++;
        this.map=map;
        this.personnages= FXCollections.observableArrayList();
        this.items= FXCollections.observableArrayList();
    }

    public void ajouterPersonnage(Personnage personnage){
        personnages.add(personnage);
    }

    public void ajouterItem(Item item){
        items.add(item);
    }

    public void supprimerItem (Item item){
        for(int i=0;i<items.size();i++){
            if(items.get(i).getId().equals(item.getId())){
                items.remove(i);
            }
        }
    }
    public void supprimerPersonnage (Personnage personnage){
        for(int i=0;i<personnages.size();i++){
            if(personnages.get(i).getId().equals(personnage.getId())){
                personnages.remove(i);
            }
        }
    }

    public ObservableList<Item> getItems() {
        return items;
    }


    public ObservableList<Personnage> getPersonnages() {
        return personnages;
    }

    public Map getMap() {
        return map;
    }

    private boolean estDansLimiteTerrain(Hitbox hitbox){

        boolean coinHautGauche=false;
        boolean coinBasGauche=false;
        boolean coinHautDroit=false;
        boolean coinBasDroite=false;

        int coinGX = (hitbox.getX())/ hitbox.getLargeur();
        int coinDX = (hitbox.getX()+ hitbox.getLargeur()-1)/ hitbox.getLargeur();

        int coinHY = (hitbox.getY())/ hitbox.getLongueur();
        int coinBY = (hitbox.getY()+ hitbox.getLongueur()-1)/ hitbox.getLongueur();

        //Connaitre les coordonées
//        System.out.println("Coin HG: (" + hitbox.getXGauche() + ", " + hitbox.getYHaut() + ")");
//        System.out.println("Coin HD: (" + hitbox.getXDroite() + ", " + hitbox.getYHaut() + ")");
//        System.out.println("Coin BG: (" + hitbox.getXGauche() + ", " + hitbox.getYBas() + ")");
//        System.out.println("Coin BD: (" + hitbox.getXDroite() + ", " + hitbox.getYBas() + ")");


        if ((coinGX>=0 && coinGX< map.getColonne()-1) && (coinHY>=0 && coinHY< map.getLigne()-1)){
            coinHautGauche=true;
        }if ((coinGX>=0 && coinGX< map.getColonne()-1)&&(coinBY>0 && coinBY<=map.getLigne()-1)){
            coinBasGauche=true;
        }if ((coinDX>0 && coinDX<=map.getColonne()-1)&&(coinHY>=0 && coinHY< map.getLigne()-1)){
            coinHautDroit=true;
        }if ((coinDX>0 && coinDX<=map.getColonne()-1)&&(coinBY>0 && coinBY<=map.getLigne()-1)){
            coinBasDroite=true;
        }
        //Connaitre la valeur du boolean
//      System.out.println("coin HG dans limites: " + coinHautGauche);
//      System.out.println("coin HD dans limites: " + coinHautDroit);
//      System.out.println("coin BG dans limites: " + coinBasGauche);
//      System.out.println("coin BD dans limites: " + coinBasDroite);

        return coinBasDroite && coinBasGauche && coinHautDroit && coinHautGauche;


    }

    public boolean estDevantObstacle(Hitbox hitbox){

        boolean coinHautGauche=false;
        boolean coinBasGauche=false;
        boolean coinHautDroit=false;
        boolean coinBasDroite=false;

        if (estDansLimiteTerrain(hitbox)){
//          Connaitre les coordonées
//        System.out.println("Coin HG: (" + hitbox.getXGauche()+ ", " + hitbox.getYHaut() + ")");
//        System.out.println(map.getMap()[hitbox.getYHaut()][hitbox.getXGauche()]);
//        System.out.println("Coin HD: (" + hitbox.getXDroite() + ", " + hitbox.getYHaut() + ")");
//        System.out.println(map.getMap()[hitbox.getYHaut()][hitbox.getXDroite()]);
//        System.out.println("Coin BG: (" + hitbox.getXGauche() + ", " + hitbox.getYBas() + ")");
//        System.out.println(map.getMap()[hitbox.getYBas()][hitbox.getXGauche()]);
//        System.out.println("Coin BD: (" + hitbox.getXDroite() + ", " + hitbox.getYBas() + ")");
//        System.out.println(map.getMap()[hitbox.getYBas()][hitbox.getXDroite()]);

            if (this.map.getMap()[hitbox.getYHaut()][hitbox.getXGauche()] == 54) {
                coinHautGauche=true;
            }if (this.map.getMap()[hitbox.getYHaut()][hitbox.getXDroite()]==54){
                coinHautDroit=true;
            }
            if (this.map.getMap()[hitbox.getYBas()][hitbox.getXGauche()]==54){
                coinBasGauche=true;
            }if (this.map.getMap()[hitbox.getYBas()][hitbox.getXDroite()]==54){
                coinBasDroite=true;
            }
            //Connaitre valeurs des booleans
//        System.out.println("coin HG"+ coinHautGauche);
//        System.out.println("coin HD"+ coinHautDroit);
//        System.out.println("coin BG"+ coinBasGauche);
//        System.out.println("coin BD"+ coinBasDroite);

            return coinBasDroite || coinBasGauche || coinHautDroit || coinHautGauche;

        }

        return true;



    }

    public boolean estDansTuile(int valeurTuile,Hitbox hitbox){

        boolean coinHautGauche=false;
        boolean coinBasGauche=false;
        boolean coinHautDroit=false;
        boolean coinBasDroite=false;

        int coinGX = (hitbox.getX())/ getMap().getTailleTuile();
        int coinDX = (hitbox.getX()+ hitbox.getLargeur()-1)/ getMap().getTailleTuile();

        int coinHY = (hitbox.getY())/ getMap().getTailleTuile();
        int coinBY = (hitbox.getY()+ hitbox.getLongueur()-1)/ getMap().getTailleTuile();

//        System.out.println("Coin HG: (" + hitbox.getXGauche() + ", " + hitbox.getYHaut() + ")");
//        System.out.println("Coin HD: (" + hitbox.getXDroite() + ", " + hitbox.getYHaut() + ")");
//        System.out.println("Coin BG: (" + hitbox.getXGauche() + ", " + hitbox.getYBas() + ")");
//        System.out.println("Coin BD: (" + hitbox.getXDroite() + ", " + hitbox.getYBas() + ")");

        if (this.map.getMap()[coinHY][coinGX] == valeurTuile) {
            coinHautGauche=true;
        }if (this.map.getMap()[coinHY][coinDX]==valeurTuile){
            coinHautDroit=true;
        }
        if (this.map.getMap()[coinBY][coinGX]==valeurTuile){
            coinBasGauche=true;
        }if (this.map.getMap()[coinBY][coinDX]==valeurTuile){
            coinBasDroite=true;
        }

//        System.out.println("coin HG"+ coinHautGauche);
//        System.out.println("coin HD"+ coinHautDroit);
//        System.out.println("coin BG"+ coinBasGauche);
//        System.out.println("coin BD"+ coinBasDroite);

        return coinBasDroite && coinBasGauche && coinHautDroit && coinHautGauche;
    }

    public String getId() {
        return id;
    }


    public void init() {

        for (int i = 0; i < 20; i++) {
            ajouterItem(new Piece(this));
        }
        for (int i =0;i<10;i++){
            ajouterItem(new Bombe(this));
        }
        for (int i =0;i<10;i++){
            ajouterPersonnage(new Ennemi1(this));
        }
        for (int i =0;i<10;i++){
            ajouterItem(new CoeurRouge(this));

        }
        for (int i=0;i<10;i++){
            ajouterItem(new CoeurBleu(this));
        }
        for (int i=0;i<5;i++){
            ajouterItem(new SuperMegaFast(this));
        }
    }



}


