package test.java ;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Bombe;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.*;
import com.example.sae_zeldalike.modele.Inventaire;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi1;

@Nested
public class MethodesTest {


    private Environnement environnementFake;


    @Test
    void reduirePointsDeVie() {

        Ennemi1 ennemi = new Ennemi1(environnementFake);                                      // Supposons qu'Ennemi1 a un constructeur prenant des points de vie
        ennemi.reduirePointsDeVie(20);
        assertEquals(80, ennemi.getPointVieProperty(), "Les points de vie de l'ennemi devraient être réduits de 20");


    }

    @Test
    void utiliserItemDansInventaire() {

        Inventaire inventaire = new Inventaire();
        Epée epee = new Epée(environnementFake, 20, 20);
        inventaire.modifieStockableInventaire(epee);
        assertTrue(inventaire.getInventaireCaseActuel().effetUtiliser(), "Link utilise l'épée avec succès");
    }

    @Test
    void emplacementInventaireLibre() {
        Inventaire inventaire = new Inventaire();
        assertEquals(6, inventaire.connaitreIndiceCaseVide().size(), "L'inventaire devrait être complètement vide au départ");
    }


    @Test
    void ajouteItemDansInventaire() {


        Inventaire inventaire = new Inventaire();
        Bombe bombe = new Bombe(environnementFake);
        inventaire.setCaseActuel(0);
        inventaire.modifieStockableInventaire(bombe);
        assertEquals(bombe, inventaire.getInventaireCase1(), "La bombe est ajoutée à la première case de l'inventaire");
    }

    @Test
    void modifieCaseInventaire() {

        Inventaire inventaire = new Inventaire();
        Arc arc = new Arc(environnementFake);
        inventaire.setCaseActuel(0);
        inventaire.modifieStockableInventaire(arc);

        assertEquals(arc, inventaire.getInventaireCase1(), "La case 1 de l'inventaire  contenient l'arc");
        inventaire.modifieStockableInventaire(null);

    }

    @Test
    void seDeplaceSansEffet() {

        Ennemi1 ennemi = new Ennemi1(environnementFake, 10, 20);

        // Cible à 10 unités sur l'axe X et 5 unités sur l'axe Y
        int cibleX = 10;
        int cibleY = 5;

        ennemi.seDeplace(cibleX, cibleY);

        // Vérifier que l'ennemi s'est déplacé correctement selon sa vitesse
        assertEquals(ennemi.getVitesseDeplacement(), ennemi.getPositionX(), "L'ennemi devrait se déplacer sur l'axe X");
        assertEquals(0, ennemi.getPositionY(), "L'ennemi ne devrait pas se déplacer sur l'axe Y pour cette étape");
    }

    @Test
    void seDeplaceAvecEffetDeRalentissement() {
        Ennemi1 ennemi = new Ennemi1(environnementFake, 10, 10);

        // Appliquer un effet de ralentissement
        ennemi.setEstToucheParEffet(true);

        ennemi.setVitesseDeplacementProperty(2);
        // l'effet ralentit la vitesse de 2 unités

        ennemi.setDureeEffet(1); // Effet actif pour 1 tour

        // Cible à 10 unités sur l'axe X et 5 unités sur l'axe Y
        int cibleX = 10;
        int cibleY = 5;

        ennemi.seDeplace(cibleX, cibleY);

        // Vérifie que l'ennemi s'est déplacé avec une vitesse réduite
        int vitesseAttendue = ennemi.getVitesseDeplacement() - 2;
        if (vitesseAttendue <= 0) {
            vitesseAttendue = 1; // La vitesse ne peut pas être inférieure à 1
        }
        assertEquals(vitesseAttendue, ennemi.getPositionX(), "L'ennemi se déplacer sur l'axe X avec une vitesse réduite");
        assertEquals(0, ennemi.getPositionY(), "L'ennemi ne se déplace pas sur l'axe Y pour cette étape");
    }

    @Test
    void seDeplaceApresEffetDeRalentissement() {

        Ennemi1 ennemi = new Ennemi1(environnementFake, 22, 22);

        // Applique  un effet de ralentissement
        ennemi.setEstToucheParEffet(true);
        ennemi.setVitesseDeplacementProperty(2);
        ennemi.setDureeEffet(1);

        // Cible à 10 unités sur l'axe X et 5 unités sur l'axe Y
        int cibleX = 10;
        int cibleY = 5;

        // Déplacement avec effet de ralentissement
        ennemi.seDeplace(cibleX, cibleY);

        // Vérifier l'état après déplacement avec ralentissement
        int vitesseAttendue = ennemi.getVitesseDeplacement() - 2;
        if (vitesseAttendue <= 0) {
            vitesseAttendue = 1;
        }
        assertEquals(vitesseAttendue, ennemi.getPositionX(), "L'ennemi se déplace  sur l'axe X avec une vitesse réduite");
        assertEquals(0, ennemi.getPositionY(), "L'ennemi ne se déplace pas sur l'axe Y pour cette étape");

        // Déplacement après fin de l'effet de ralentissement
        ennemi.seDeplace(cibleX, cibleY);

        assertEquals(vitesseAttendue + ennemi.getVitesseDeplacement(), ennemi.getPositionX(), "L'ennemi se déplace avec sa vitesse normale après la fin de l'effet de ralentissement");
        assertEquals(0, ennemi.getPositionY(), "L'ennemi ne se déplace pas sur l'axe Y pour cette étape");
    }
}