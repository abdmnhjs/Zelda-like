package com.example.sae_zeldalike;

import com.example.sae_zeldalike.modele.Environnement;
import com.example.sae_zeldalike.modele.Link;
import com.example.sae_zeldalike.modele.Personnage;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    Environnement environnement = new Environnement();
    Link link = new Link(environnement, 5, 5);

    @FXML
    private Pane terrain;

    private void creerSprite(Personnage personnage){
        Circle circle = new Circle(5);
        circle.setFill(Color.BLACK);
        circle.setId(personnage.getId());
        circle.translateXProperty().bind(personnage.getPositionXProperty());
        circle.translateYProperty().bind(personnage.getPositionYProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
