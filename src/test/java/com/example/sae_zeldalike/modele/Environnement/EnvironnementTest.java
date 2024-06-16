package com.example.sae_zeldalike.modele.Environnement;

import com.example.sae_zeldalike.modele.Personnage.Link;
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
        ObservableList<Link> observableListLink = FXCollections.observableArrayList();
        Link link = new Link(environnement);
        // Ajouter le lien à la liste observable
        observableListLink.add(link);
        // Vérifier si la liste observable contient le lien ajouté
        assertTrue(observableListLink.contains(link));
    }


    @Test
    void ajouterPersonnage() {
    }

    @Test
    void ajouterItem() {
    }

    @Test
    void supprimerItem() {
    }

    @Test
    void supprimerProjectiles() {
    }

    @Test
    void supprimerLink() {
    }

    @Test
    void supprimerPersonnage() {
    }

    @Test
    void ajouterProjectiles() {
    }

    @Test
    void getProjectiles() {
    }

    @Test
    void getItems() {
    }

    @Test
    void getPersonnages() {
    }

    @Test
    void getMap() {
    }

    @Test
    void estDevantObstacle() {
    }

    @Test
    void estDansTuile() {
    }

    @Test
    void estDansLaZone() {
    }

    @Test
    void getId() {
    }

    @Test
    void getLinkRemovalQueue() {
    }

    @Test
    void init() {
    }
}