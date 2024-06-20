package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Environnement.Map;
import com.example.sae_zeldalike.modele.Inventaire;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.NonStockable.CoeurRouge;
import com.example.sae_zeldalike.modele.Item.NonStockable.Effet;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Epée;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Stockable;
import javafx.beans.property.IntegerProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LinkTest {

    private Map map;
    private Environnement environnement;
    private Link link;

    @BeforeEach
    void setUp() throws IOException {
        this.map = new Map("src/main/resources/1erTerrain.json");
        environnement = new Environnement(map);
        link = new Link(environnement);
    }
    @Test
    void getArmeEquiper() {
        Epée epee = new Epée(environnement);
        link.setArmeEquiper(epee);
        assertEquals(epee, link.getArmeEquiper());
    }

    @Test
    void setArmeEquiper() {
        Epée epee = new Epée(environnement);
        link.setArmeEquiper(epee);
        assertEquals(epee, link.getArmeEquiper());
    }

    @Test
    void ajouterBouclier() {
        link.ajouterBouclier(10);
        assertEquals(10, link.getPointDeVieAdditionelle());
    }

    @Test
    void getPointDeVieAdditionelle() {
        assertEquals(0, link.getPointDeVieAdditionelle());
        link.ajouterBouclier(10);
        assertEquals(10, link.getPointDeVieAdditionelle());
    }

    @Test
    void getPointDeVieAdditionelleProperty() {
        IntegerProperty property = link.getPointDeVieAdditionelleProperty();
        assertNotNull(property);
        assertEquals(0, property.get());
        link.ajouterBouclier(10);
        assertEquals(10, property.get());
    }

    @Test
    void getPointDeVieMax() {
        assertEquals(link.getPointDeVieAdditionelle() + link.getPointVie(), link.getPointDeVieMax());
    }

    @Test
    void reduirePointsDeVie() {
        link.ajouterBouclier(10);
        link.reduirePointsDeVie(5);
        assertEquals(5, link.getPointDeVieAdditionelle());
        assertEquals(100, link.getPointVie());

        link.reduirePointsDeVie(10);
        assertEquals(0, link.getPointDeVieAdditionelle());
        assertEquals(95, link.getPointVie());
        link.reduirePointsDeVie(100000);
        assertEquals(0,link.getPointDeVieAdditionelle());
        assertEquals(0,link.getPointVie());
    }

    @Test
    void getInventaire() {
        Inventaire inventaire = link.getInventaire();
        assertNotNull(inventaire);
    }

    @Test
    void utiliserItemDansInventaire() {
        Epée epee = new Epée(environnement);
        link.getInventaire().modifieStockableInventaire(epee);
        link.utiliserItemDansInventaire();
        assertEquals(epee, link.getArmeEquiper());
    }

    @Test
    void emplacementInventaireLibre() {
        assertTrue(link.emplacementInventaireLibre());
    }

    @Test
    void modifieCaseInventaire() {
        Stockable item = new Stockable() {
            @Override
            public Item getItem() {
                return null;
            }

            @Override
            public boolean effetUtiliser() {
                return false;
            }

            @Override
            public void utiliserCapacite() {

            }
        };
        link.modifieCaseInventaire(item);
        assertEquals(item, link.getInventaire().getInventaireCaseActuel());
    }

    @Test
    void essaiRamasserItem() {
        Item item = new CoeurRouge(environnement);
        item.setPositionX(link.getPositionX());
        item.setPositionY(link.getPositionY());
        environnement.ajouterItem(item);
        assertEquals(item, link.essaiRamasserItem());
    }

    @Test
    void getEffets() {
        assertNotNull(link.getEffets());
    }

    @Test
    void ajouterEffet() {
        Effet effet = new Effet() {
            @Override
            public void appliquer(Personnage personnage) {

            }
        };
        link.ajouterEffet(effet);
        assertTrue(link.getEffets().contains(effet));
    }

    @Test
    void getPortefeuilleProperty() {
        IntegerProperty portefeuille = link.getPortefeuilleProperty();
        assertNotNull(portefeuille);
    }

    @Test
    void ajouterPiece() {
        link.ajouterPiece(10);
        assertEquals(10, link.getPortefeuilleProperty().get());
    }
}