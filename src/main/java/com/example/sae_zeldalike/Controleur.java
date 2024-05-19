package com.example.sae_zeldalike;

import com.example.sae_zeldalike.Vue.VueMap;
import com.example.sae_zeldalike.modele.Map;
import com.example.sae_zeldalike.modele.Environnement;
import com.example.sae_zeldalike.modele.Link;
import com.example.sae_zeldalike.modele.Personnage;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement environnement;
    private Link link;
    private Map map;
    private VueMap vueMap;

    @FXML
    private Pane pane;
    @FXML
    private TilePane tilePane;


    public void creerSprite(Personnage personnage){
        Circle circle = new Circle(5);
        circle.setFill(Color.BLACK);
        circle.setId(personnage.getId());
        circle.translateXProperty().bind(personnage.getPositionXProperty());
        circle.translateYProperty().bind(personnage.getPositionYProperty());
        this.pane.getChildren().add(circle);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.map= new Map();
        this.environnement = new Environnement(map);
        this.link = new Link(environnement, 5, 5);
        vueMap = new VueMap(tilePane,map);
        creerSprite(link);
        this.pane.requestFocus();
        Clavier clavier=new Clavier(link);
        this.pane.setOnKeyPressed(clavier);
    }

    public void afficher(MouseEvent mouseEvent){
        this.pane.requestFocus();
    }

}
