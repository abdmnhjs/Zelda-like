package com.example.sae_zeldalike;

import javafx.fxml.Initializable;
import modele.Environnement;

import java.net.URL;
import java.util.ResourceBundle;

public class Controlleur implements Initializable {

    private Environnement environnement;
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        environnement = new Environnement(660,425);
    }
}
