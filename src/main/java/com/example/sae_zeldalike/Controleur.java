package com.example.sae_zeldalike;

import com.example.sae_zeldalike.modele.Environnement;
import com.example.sae_zeldalike.modele.Link;
import com.example.sae_zeldalike.modele.Map;
import com.example.sae_zeldalike.modele.Personnage;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    private Environnement environnement;
    private Link link;
    private Map map;
    @FXML
    private Pane terrain = new Pane();

    private Clavier clavier;

    public Clavier getClavier(){
        return this.clavier;
    }

    public void creerSprite(Personnage personnage){
        Circle circle = new Circle(5);
        circle.setFill(Color.BLACK);
        circle.setId(personnage.getId());
        circle.translateXProperty().bind(personnage.getPositionXProperty());
        circle.translateYProperty().bind(personnage.getPositionYProperty());
        this.terrain.getChildren().add(circle);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.map = new Map();
        this.environnement = new Environnement(map);
        this.link = new Link(environnement, 5, 5);
        creerSprite(link);
        this.clavier = new Clavier(link);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.Z, false, false, false, false);
        this.clavier.handle(keyEvent);
        this.terrain.setOnKeyPressed(clavier);
        this.terrain.requestFocus();
    }

}
