package com.example.sae_zeldalike.Controlleur;

import com.example.sae_zeldalike.modele.Environnement.Map;
import com.example.sae_zeldalike.modele.Personnage.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Clavier implements EventHandler<KeyEvent> {

    private Personnage personnage;
    private Map map;

    public Clavier(Personnage personnage, Map map) {
        this.personnage = personnage;
        this.map = map;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        evenementTouche(keyEvent);
    }

    private void evenementTouche(KeyEvent keyEvent) {
        int positionX = personnage.getPositionX();
        int positionY = personnage.getPositionY();
        int vitesseDeplacement = personnage.getVitesseDeplacement();
        int newX = positionX;
        int newY = positionY;

        switch (keyEvent.getCode()) {
            case Z -> newY -= vitesseDeplacement;
            case S -> newY += vitesseDeplacement;
            case Q -> newX -= vitesseDeplacement;
            case D -> newX += vitesseDeplacement;
        }

        if (coordonneesPossibles(newX, newY)) {
            personnage.setPositionXProperty(newX);
            personnage.setPositionYProperty(newY);
            System.out.println("Position X : " + personnage.getPositionX() + " Position Y : " + personnage.getPositionY());
        }
    }

    private boolean coordonneesPossibles(int newX, int newY) {
        boolean withinBounds = newX >= 0 && newY >= 0 && newX < map.getColonne() * 32 && newY < map.getLigne() * 32;
        if (!withinBounds) {
            return false;
        }

        int tileSize = 32;
        int topLeftX = newX;
        int topLeftY = newY;
        int bottomRightX = newX + tileSize - 1;
        int bottomRightY = newY + tileSize - 1;

        boolean topLeft = isTileWalkable(topLeftX, topLeftY);
        boolean bottomRight = isTileWalkable(bottomRightX, bottomRightY);

        return topLeft && bottomRight;
    }

    private boolean isTileWalkable(int x, int y) {
        int tileSize = 32;
        int gridX = x / tileSize;
        int gridY = y / tileSize;

        // Ajustement de l'index pour éviter le décalage
        int index = gridY * map.getColonne() + gridX;
        System.out.println("INDEX : " + index);
        if (index < 0 || index >= map.getMap().length) {
            return false;
        }

        return map.getMap()[index] != 54;
    }
}