package com.example.sae_zeldalike.modele.Environnement;

import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arc;
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
    private ObservableList<Item> items;
    private ObservableList<Projectile> projectiles;
    private ObservableList<Link> linkRemovalQueue;
    private Map map;

    public Environnement(Map map){

        this.id = "E"+compteurEnvironnement;
        compteurEnvironnement++;
        this.map=map;
        this.projectiles = FXCollections.observableArrayList();
        this.personnages= FXCollections.observableArrayList();
        this.items= FXCollections.observableArrayList();
        this.linkRemovalQueue = FXCollections.observableArrayList();
    }

    public void ajouterLink(Link link){
        this.linkRemovalQueue.add(link);
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

    public void supprimerProjectiles(Projectile projectile){
        for(int i=0;i<projectiles.size();i++){
            if(projectiles.get(i).getId().equals(projectile.getId())){
                projectiles.remove(i);
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

    public void ajouterProjectiles(Projectile projectile){
        projectiles.add(projectile);
    }

    public ObservableList<Projectile> getProjectiles() {
        return this.projectiles;
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

        if ((coinGX>=0 && coinGX< map.getColonne()-1) && (coinHY>=0 && coinHY< map.getLigne()-1)){
            coinHautGauche=true;
        }if ((coinGX>=0 && coinGX< map.getColonne()-1)&&(coinBY>0 && coinBY<=map.getLigne()-1)){
            coinBasGauche=true;
        }if ((coinDX>0 && coinDX<=map.getColonne()-1)&&(coinHY>=0 && coinHY< map.getLigne()-1)){
            coinHautDroit=true;
        }if ((coinDX>0 && coinDX<=map.getColonne()-1)&&(coinBY>0 && coinBY<=map.getLigne()-1)){
            coinBasDroite=true;
        }

        return coinBasDroite && coinBasGauche && coinHautDroit && coinHautGauche;


    }

    public boolean estDevantObstacle(Hitbox hitbox){

        boolean coinHautGauche=false;
        boolean coinBasGauche=false;
        boolean coinHautDroit=false;
        boolean coinBasDroite=false;

        if (estDansLimiteTerrain(hitbox)){

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
        return coinBasDroite && coinBasGauche && coinHautDroit && coinHautGauche;
    }



    public boolean estDansLaZone(Hitbox hitbox1, Hitbox hitbox2) {

        int hitbox1X = hitbox1.getX();
        int hitbox1Y = hitbox1.getY();
        int hitbox1Largeur = hitbox1.getLargeur();
        int hitbox1Longueur = hitbox1.getLongueur();

        int hitbox2X = hitbox2.getX();
        int hitbox2Y = hitbox2.getY();
        int hitbox2Largeur = hitbox2.getLargeur();
        int hitbox2Longueur = hitbox2.getLongueur();

        boolean chevauchementHorizontal = hitbox1X < hitbox2X + hitbox2Largeur && hitbox1X + hitbox1Largeur > hitbox2X;

        boolean chevauchementVertical = hitbox1Y < hitbox2Y + hitbox2Longueur && hitbox1Y + hitbox1Longueur > hitbox2Y;

        return chevauchementHorizontal && chevauchementVertical;
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
            ajouterPersonnage(new Ennemi2(this));
        }
        for (int i =0;i<15;i++){
            ajouterItem(new CoeurRouge(this));
            ajouterItem(new CoeurBleu(this));
        }

        for (int i=0;i<5;i++){
            ajouterItem(new SuperMegaFast(this));
        }for (int i=0;i<2;i++){
            ajouterItem(new Poison(this));
        }for (int i=0;i<4;i++) {
            ajouterItem(new Epée(this));
            ajouterItem(new Arc(this));
        }
    }



}


