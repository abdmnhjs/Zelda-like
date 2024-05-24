    package com.example.sae_zeldalike.Controlleur;

    import com.example.sae_zeldalike.modele.Personnage.*;
    import javafx.beans.property.SimpleStringProperty;
    import javafx.beans.property.StringProperty;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.collections.ObservableSet;
    import javafx.event.EventHandler;
    import javafx.scene.input.KeyCode;
    import javafx.scene.input.KeyEvent;

    public class Clavier implements EventHandler<KeyEvent> {

        private Personnage personnage;


        public Clavier(Personnage p) {
            this.personnage = p;
        }

        @Override
        public void handle(KeyEvent keyEvent) {
            interactionTouche(keyEvent);
        }


        private void interactionTouche(KeyEvent keyEvent) {
            int vitesseDeplacement = this.personnage.getVitesseDeplacement();

            switch (keyEvent.getCode()) {
                case Z -> {
                    int newY = this.personnage.getPositionY() - vitesseDeplacement;
                    if (!this.personnage.getEnvironnement().estDevantObstacle(this.personnage.getPositionX(), newY)) {
                        if(!this.personnage.getEnvironnement().estDansLimiteTerrain(personnage.getPositionX(), newY)){
                            this.personnage.setPositionYProperty(newY);
                        }
                    }
                }
                case S -> {
                    int newY = this.personnage.getPositionY() + vitesseDeplacement;
                    if (!this.personnage.getEnvironnement().estDevantObstacle(this.personnage.getPositionX(), newY)) {
                        if(!this.personnage.getEnvironnement().estDansLimiteTerrain(personnage.getPositionX(), newY)){
                            this.personnage.setPositionYProperty(newY);
                        }
                    }
                }
                case D -> {
                    int newX = this.personnage.getPositionX() + vitesseDeplacement;
                    if (!this.personnage.getEnvironnement().estDevantObstacle(newX, this.personnage.getPositionY())) {
                        if(!this.personnage.getEnvironnement().estDansLimiteTerrain(newX, this.personnage.getPositionY())){
                            this.personnage.setPositionXProperty(newX);
                        }

                    }
                }
                case Q -> {
                    int newX = this.personnage.getPositionX() - vitesseDeplacement;
                    if (!this.personnage.getEnvironnement().estDevantObstacle(newX, this.personnage.getPositionY())) {
                        if(!this.personnage.getEnvironnement().estDansLimiteTerrain(newX, this.personnage.getPositionY())){
                            this.personnage.setPositionXProperty(newX);
                        }
                    }
                }
            }
            System.out.println("Position X : " + personnage.getPositionX() + " Position Y : " + personnage.getPositionY());
        }
    }
