package com.example.sae_zeldalike.modele.Environnement;

import com.example.sae_zeldalike.modele.Hitbox;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private String id;
    private static int compteurEnvironnement=0;
    private ObservableList<Personnage> personnages;
    private Map map;

    public Environnement(Map map){

        this.id = "E"+compteurEnvironnement;
        compteurEnvironnement++;
        this.map=map;
        this.personnages= FXCollections.observableArrayList();
    }

    public void ajouterPersonnage(Personnage personnage){
        personnages.add(personnage);
    }

    public ObservableList<Personnage> getPersonnages() {
        return personnages;
    }

    public Map getMap() {
        return map;
    }

    public boolean estDansLimiteTerrain(Hitbox hitbox){

        boolean coinHautGauche=false;
        boolean coinBasGauche=false;
        boolean coinHautDroit=false;
        boolean coinBasDroite=false;

        int coinHGX = (hitbox.getX())/ hitbox.getLargeur();
        int coinHGY = (hitbox.getY())/ hitbox.getLongueur();

        int coinHDX = (hitbox.getX()+ hitbox.getLargeur()-1)/ hitbox.getLargeur();
        int coinHDY = (hitbox.getY())/ hitbox.getLongueur();

        int coinBGX = hitbox.getX()/ hitbox.getLargeur();
        int coinBGY = (hitbox.getY()+ hitbox.getLongueur()-1)/ hitbox.getLongueur();

        int coinBDX = (hitbox.getX()+ hitbox.getLargeur()-1)/ hitbox.getLargeur();
        int coinBDY = (hitbox.getY()+ hitbox.getLongueur()-1)/ hitbox.getLongueur();

        //Connaitre les coordonées
//        System.out.println("Coin HG: (" + coinHGX + ", " + coinHGY + ")");
//        System.out.println("Coin HD: (" + coinHDX + ", " + coinHDY + ")");
//        System.out.println("Coin BG: (" + coinBGX + ", " + coinBGY + ")");
//        System.out.println("Coin BD: (" + coinBDX + ", " + coinBDY + ")");

        if ((coinHGX>=0 && coinHGX<map.getColonne()-1) && (coinHGY>=0 && coinHGY<map.getLigne()-1)){
            coinHautGauche=true;
        }if ((coinBGX>=0 && coinBGX<map.getColonne()-1)&&(coinBGY>0 && coinBGY<map.getLigne()-1)){
            coinBasGauche=true;
        }if ((coinHDX>=0 && coinHDX<=map.getColonne()-1)&&(coinHDY>=0 && coinHDY<map.getLigne()-1)){
            coinHautDroit=true;
        }if ((coinBDX>0 && coinBDX<=map.getColonne()-1)&&(coinBDY>0 && coinBDY<=map.getLigne()-1)){
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

        int coinHGX = (hitbox.getX())/ hitbox.getLargeur();
        int coinHGY = (hitbox.getY())/ hitbox.getLongueur();

        int coinHDX = (hitbox.getX()+ hitbox.getLargeur()-1)/ hitbox.getLargeur();
        int coinHDY = (hitbox.getY())/ hitbox.getLongueur();

        int coinBGX = hitbox.getX()/ hitbox.getLargeur();
        int coinBGY = (hitbox.getY()+ hitbox.getLongueur()-1)/ hitbox.getLongueur();

        int coinBDX = (hitbox.getX()+ hitbox.getLargeur()-1)/ hitbox.getLargeur();
        int coinBDY = (hitbox.getY()+ hitbox.getLongueur()-1)/ hitbox.getLongueur();

//        Connaitre les coordonées
//        System.out.println("Coin HG: (" + coinHGX + ", " + coinHGY + ")");
//        System.out.println("Coin HD: (" + coinHDX + ", " + coinHDY + ")");
//        System.out.println("Coin BG: (" + coinBGX + ", " + coinBGY + ")");
//        System.out.println("Coin BD: (" + coinBDX + ", " + coinBDY + ")");

        if (!estDansLimiteTerrain(hitbox)){
            return true;
        }

        if (this.map.getMap()[(coinHGY * map.getColonne())+coinHGX] == 54) {
            System.out.println(map.getMap()[(coinHGY* map.getColonne())+coinHGX]);
            coinHautGauche=true;
        }if (this.map.getMap()[(coinHDY* map.getColonne())+coinHDX]==54){
            coinHautDroit=true;
        }if (this.map.getMap()[(coinBGY* map.getColonne())+coinBGX]==54){
            coinBasGauche=true;
        }if (this.map.getMap()[(coinBDY*map.getColonne())+coinBDX]==54){
            coinBasDroite=true;
        }
        //Connaitre valeurs des booleans
//        System.out.println("coin HG"+ coinHautGauche);
//        System.out.println("coin HD"+ coinHautDroit);
//        System.out.println("coin BG"+ coinBasGauche);
//        System.out.println("coin BD"+ coinBasDroite);

        return coinBasDroite || coinBasGauche || coinHautDroit || coinHautGauche;

    }

    public String getId() {
        return id;
    }


}


