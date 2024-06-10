package com.example.sae_zeldalike.modele.Environnement;

import com.example.sae_zeldalike.modele.Projectile.BouleDeFeu;
import com.example.sae_zeldalike.modele.Hitbox;
import com.example.sae_zeldalike.modele.Item.*;
import com.example.sae_zeldalike.modele.Item.NonStockable.CoeurBleu;
import com.example.sae_zeldalike.modele.Item.NonStockable.CoeurRouge;
import com.example.sae_zeldalike.modele.Item.NonStockable.Poison;
import com.example.sae_zeldalike.modele.Item.NonStockable.SuperMegaFast;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Epée;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Bombe;
import com.example.sae_zeldalike.modele.Item.StockableDansPortefeuille.Piece;
import com.example.sae_zeldalike.modele.Personnage.*;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi1;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi2;
import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private String id;
    private static int compteurEnvironnement=0;
    private ObservableList<Personnage> personnages;
    private ObservableList<Ennemi2> ennemis2;
    private ObservableList<Item> items;
    private ObservableList<Projectile> flèchesEnDéplacement;
    private ObservableList<Epée> epeeEnMain;
    private ObservableList<Link> linkRemovalQueue;
    private ObservableList<BouleDeFeu> boulesDeFeuEnDeplacement;
    private Map map;

    public Environnement(Map map){

        this.id = "E"+compteurEnvironnement;
        compteurEnvironnement++;
        this.map=map;
        this.flèchesEnDéplacement = FXCollections.observableArrayList();
        this.personnages= FXCollections.observableArrayList();
        this.ennemis2 = FXCollections.observableArrayList();
        this.items= FXCollections.observableArrayList();
        this.epeeEnMain = FXCollections.observableArrayList();
        this.linkRemovalQueue = FXCollections.observableArrayList();
        this.boulesDeFeuEnDeplacement = FXCollections.observableArrayList();
    }

    public void ajouterLink(Link link){
        this.linkRemovalQueue.add(link);
    }

    public void ajouterPersonnage(Personnage personnage){
        personnages.add(personnage);
    }

    public void ajouterEnnemi2(Ennemi2 ennemi2){
        ennemis2.add(ennemi2);
    }

    public void ajouterItem(Item item){
        items.add(item);
    }

    public void ajouterBouleDeFeu(BouleDeFeu bouleDeFeu){
        boulesDeFeuEnDeplacement.add(bouleDeFeu);
    }

    public void supprimerItem (Item item){
        for(int i=0;i<items.size();i++){
            if(items.get(i).getId().equals(item.getId())){
                items.remove(i);
            }
        }
    }

    public void supprimerEpee(Epée epée){
        for(int i=0;i<epeeEnMain.size();i++){
            if(epeeEnMain.get(i).getId().equals(epée.getId())){
                epeeEnMain.remove(i);
            }
        }
    }

    public void supprimerFleche(Projectile projectile){
        for(int i=0;i<flèchesEnDéplacement.size();i++){
            if(flèchesEnDéplacement.get(i).getId().equals(projectile.getId())){
                flèchesEnDéplacement.remove(i);
            }
        }
    }

    public void supprimerLink (Link link){
        for(int i=0;i<linkRemovalQueue.size();i++){
            if(linkRemovalQueue.get(i).getId().equals(link.getId())){
                linkRemovalQueue.remove(i);
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

    public void supprimerEnnemi2 (Ennemi2 ennemi2){
        for(int i=0;i<ennemis2.size();i++){
            if(ennemis2.get(i).getId().equals(ennemi2.getId())){
                ennemis2.remove(i);
            }
        }
    }

    public void supprimerBouleDeFeu(BouleDeFeu bouleDeFeu){
        for(int i = 0; i< boulesDeFeuEnDeplacement.size(); i++){
            if(boulesDeFeuEnDeplacement.get(i).getId().equals(bouleDeFeu.getId())){
                boulesDeFeuEnDeplacement.remove(i);
            }
        }
    }

    public ObservableList<Projectile> getFlèchesEnDéplacement() {
        return this.flèchesEnDéplacement;
    }


    public ObservableList<Item> getItems() {
        return items;
    }

    public ObservableList<Epée> getEpeeEnMain() {
        return epeeEnMain;
    }

    public ObservableList<Personnage> getPersonnages() {
        return personnages;
    }

    public ObservableList<Ennemi2> getEnnemis2() {
        return ennemis2;
    }

    public ObservableList<BouleDeFeu> getBoulesDeFeuEnDeplacement() {
        return boulesDeFeuEnDeplacement;
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

    public ObservableList<Link> getLinkRemovalQueue() {
        return this.linkRemovalQueue;
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
            ajouterEnnemi2(new Ennemi2(this));
        }
        for (int i =0;i<10;i++){
            ajouterItem(new CoeurRouge(this));

        }
        for (int i=0;i<10;i++){
            ajouterItem(new CoeurBleu(this));
        }
        for (int i=0;i<5;i++){
            ajouterItem(new SuperMegaFast(this));
        }for (int i=0;i<5;i++){
            ajouterItem(new Poison(this));
        }
    }



}


