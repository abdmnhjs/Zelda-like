package com.example.sae_zeldalike.modele.Environnement;

import com.example.sae_zeldalike.modele.Hitbox;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.NonStockable.CoeurRouge;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arc;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi1;
import com.example.sae_zeldalike.modele.Personnage.Link;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import com.example.sae_zeldalike.modele.Projectile.Fleche;
import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class EnvironnementTest {

    @Test
    void ajouterLink() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Link link = new Link(environnement);

        environnement.ajouterLink(link);

        assertTrue(environnement.getLinkRemovalQueue().contains(link));
    }



    @Test
    void ajouterPersonnage() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Personnage personnage = new Ennemi1(environnement);

        environnement.ajouterPersonnage(personnage);

        assertTrue(environnement.getPersonnages().contains(personnage));
    }


    @Test
    void ajouterItem() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Item item = new CoeurRouge(environnement);

        environnement.ajouterItem(item);

        assertTrue(environnement.getItems().contains(item));
    }


    @Test
    void supprimerItem() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Item item = new CoeurRouge(environnement);

        environnement.ajouterItem(item);
        environnement.supprimerItem(item);

        assertFalse(environnement.getItems().contains(item));
    }


    @Test
    void supprimerProjectiles() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Projectile projectile;
        projectile = new Fleche(environnement, 15, 15, 32, 32, 30, new Arc(environnement), "UP");
        environnement.ajouterProjectiles(projectile);
        environnement.supprimerProjectiles(projectile);
        assertFalse(environnement.getProjectiles().contains(projectile));
    }


    @Test
    void supprimerLink() throws IOException {
        Map map = new Map("file:src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Link link = new Link(environnement);
        environnement.ajouterLink(link);
        environnement.supprimerLink(link);
        assertFalse(environnement.getLinkRemovalQueue().contains(link));
    }


    @Test
    void supprimerPersonnage() throws IOException {
        Map map = new Map("file:src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Personnage personnage = new Ennemi1(environnement);
        environnement.ajouterPersonnage(personnage);
        environnement.supprimerPersonnage(personnage);
        assertFalse(environnement.getPersonnages().contains(personnage));
    }


    @Test
    void ajouterProjectiles() throws IOException {
        Map map = new Map("file:src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Projectile projectile = new Fleche(environnement, 15, 15, 32, 32, 30, 30, "UP");
        environnement.ajouterProjectiles(projectile);
        assertTrue(environnement.getProjectiles().contains(projectile));
    }


    @Test
    void getProjectiles() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Projectile projectile = new Fleche(environnement, 15, 15, 32, 32, 30, new Arc(environnement), "UP");
        environnement.ajouterProjectiles(projectile);

        ObservableList<Projectile> projectiles = environnement.getProjectiles();
        assertNotNull(projectiles);
        assertTrue(projectiles.contains(projectile));
    }

    @Test
    void getItems() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Item item = new CoeurRouge(environnement);
        environnement.ajouterItem(item);

        ObservableList<Item> items = environnement.getItems();
        assertNotNull(items);
        assertTrue(items.contains(item));
    }

    @Test
    void getPersonnages() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Personnage personnage = new Ennemi1(environnement);
        environnement.ajouterPersonnage(personnage);

        ObservableList<Personnage> personnages = environnement.getPersonnages();
        assertNotNull(personnages);
        assertTrue(personnages.contains(personnage));
    }

    @Test
    void getMap() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        assertEquals(map, environnement.getMap());
    }

    @Test
    void estDevantObstacle() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Hitbox hitbox = new Hitbox(0, 0, 1, 1);

        // Assume that tile (0, 0) is an obstacle
        map.getMap()[0][0] = 54;

        assertTrue(environnement.estDevantObstacle(hitbox));
    }


    @Test
    void estDansTuile() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Hitbox hitbox = new Hitbox(0, 0, 1, 1);
        map.getMap()[0][0] = 10;
        assertTrue(environnement.estDansTuile(10, hitbox));
    }


    @Test
    void estDansLaZone() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Hitbox hitbox1 = new Hitbox(0, 0, 1, 1);
        Hitbox hitbox2 = new Hitbox(0, 0, 2, 2);
        assertTrue(environnement.estDansLaZone(hitbox1, hitbox2));
    }


    @Test
    void getId() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        assertNotNull(environnement.getId());
        assertTrue(environnement.getId().startsWith("E"));
    }

    @Test
    void getLinkRemovalQueue() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        Link link = new Link(environnement);
        environnement.ajouterLink(link);
        ObservableList<Link> linkRemovalQueue = environnement.getLinkRemovalQueue();
        assertNotNull(linkRemovalQueue);
        assertTrue(linkRemovalQueue.contains(link));
    }

    @Test
    void init() throws IOException {
        Map map = new Map("src/main/resources/terrain2.json");
        Environnement environnement = new Environnement(map);
        environnement.init();
        assertTrue(environnement.getItems().size() > 0);
        assertTrue(environnement.getPersonnages().size() > 0);
    }
}