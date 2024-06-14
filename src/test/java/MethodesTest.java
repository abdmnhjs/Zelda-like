package com.example.sae_zeldalike.Test;

import com.example.sae_zeldalike.modele.Environnement.Environnement;
import com.example.sae_zeldalike.modele.Personnage.Ennemi.Ennemi1;
import com.example.sae_zeldalike.modele.Environnement.Map ;


public class MethodesTest {


    private Map mapFake ;

    @Test

    public void testDeplacementEnnemi1() {
        Environnement environnementFake = new Environnement(mapFake);
        int positionInitialeX = 0;
        int positionInitialeY = 0;


        int positionCibleX = 100;
        int positionCibleY = 100;



        Ennemi1 ennemi = new Ennemi1(environnementFake);

        // Déplacement de l'ennemi vers la cible
        ennemi.seDeplace(positionCibleX, positionCibleY);


        assertTrue(ennemi.getPositionX() == positionCibleX && ennemi.getPositionY() == positionCibleY);

    }


}

