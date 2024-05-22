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

    public boolean estDansLimiteTerrain(int x,int y){
        return ((x>0&&x<((map.getColonne()-1)*32))&&(y>0 && y<((map.getLigne()-1)*32)));

    }

    public boolean estDevantObstacle(Hitbox hitbox){

        boolean coinHautGauche=false;
        boolean coinBasGauche=false;
        boolean coinHautDroit=false;
        boolean coinBasDroite=false;

        int coinHGX = (hitbox.getX())/ hitbox.getLargeur();
        int coinHGY = (hitbox.getY())/ hitbox.getLongueur();
        System.out.println(coinHGX);
        System.out.println(coinHGY);

        int coinHDX = (hitbox.getX()+ hitbox.getLargeur()-1)/ hitbox.getLargeur();
        int coinHDY = (hitbox.getY())/ hitbox.getLongueur();
        System.out.println(coinHDX);
        System.out.println(coinHDY);

        int coinBGX = hitbox.getX()/ hitbox.getLargeur();
        int coinBGY = (hitbox.getY()+ hitbox.getLongueur())/ hitbox.getLongueur();
        System.out.println(coinBGX);
        System.out.println(coinBGY);

        int coinBDX = (hitbox.getX()+ hitbox.getLargeur())/ hitbox.getLargeur();
        int coinBDY = (hitbox.getY()+ hitbox.getLongueur())/ hitbox.getLongueur();
        System.out.println(coinBDX);
        System.out.println(coinBDY);


        if (this.map.getMap()[(coinHGY * map.getLigne())+coinHGX] == 54) {
            System.out.println(map.getMap()[(coinHGY* map.getLigne())+coinHGX]);
            coinHautGauche=true;
        }if (this.map.getMap()[(coinHDY* map.getLigne())+coinHDX]==54){
            coinHautDroit=true;
        }if (this.map.getMap()[(coinBGY* map.getLigne())+coinBGX]==54){
            coinBasGauche=true;
        }if (this.map.getMap()[(coinBDY*map.getLigne())+coinBDX]==54){
            coinBasDroite=true;
        }

        System.out.println("coin HG"+ coinHautGauche);
        System.out.println("coin HD"+ coinHautDroit);
        System.out.println("coin BG"+ coinBasGauche);
        System.out.println("coin BD"+ coinBasDroite);

        return coinBasDroite || coinBasGauche || coinHautDroit || coinHautGauche;



    }
//public boolean estDevantObstacle(Hitbox hitbox){
//
//    boolean coinHautGauche = false;
//    boolean coinBasGauche = false;
//    boolean coinHautDroit = false;
//    boolean coinBasDroite = false;
//
//    int tileSize = 32; // Assuming a method or constant that provides the tile size
//
//    int coinHGX = hitbox.getX() / tileSize;
//    int coinHGY = hitbox.getY() / tileSize;
//    int coinHDX = (hitbox.getX() + hitbox.getLargeur()) / tileSize;
//    int coinHDY = hitbox.getY() / tileSize;
//    int coinBGX = hitbox.getX() / tileSize;
//    int coinBGY = (hitbox.getY() + hitbox.getLongueur()) / tileSize;
//    int coinBDX = (hitbox.getX() + hitbox.getLargeur()) / tileSize;
//    int coinBDY = (hitbox.getY() + hitbox.getLongueur()) / tileSize;
//
//    // Debugging output
//    System.out.println("coinHGX: " + coinHGX + ", coinHGY: " + coinHGY);
//    System.out.println("coinHDX: " + coinHDX + ", coinHDY: " + coinHDY);
//    System.out.println("coinBGX: " + coinBGX + ", coinBGY: " + coinBGY);
//    System.out.println("coinBDX: " + coinBDX + ", coinBDY: " + coinBDY);
//
//    int[] map = this.map.getMap();
//    int ligne = this.map.getLigne();
//
//    if (map[coinHGY * ligne + coinHGX] == 11) {
//        coinHautGauche = true;
//    }
//    if (map[coinHDY * ligne + coinHDX] == 11) {
//        coinHautDroit = true;
//    }
//    if (map[coinBGY * ligne + coinBGX] == 11) {
//        coinBasGauche = true;
//    }
//    if (map[coinBDY * ligne + coinBDX] == 11) { // Corrected typo here
//        coinBasDroite = true;
//    }
//
//    // Debugging output
//    System.out.println("coin HG: " + coinHautGauche);
//    System.out.println("coin HD: " + coinHautDroit);
//    System.out.println("coin BG: " + coinBasGauche);
//    System.out.println("coin BD: " + coinBasDroite);
//
//    return coinBasDroite || coinBasGauche || coinHautDroit || coinHautGauche;
//}



    public String getId() {
        return id;
    }


}


