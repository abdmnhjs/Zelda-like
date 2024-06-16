package com.example.sae_zeldalike.Controlleur.MenuJeu;

import com.example.sae_zeldalike.Controlleur.Controleur;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControleurMenuJeu {




    @FXML
    private void jouer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sae_zeldalike/vue1.fxml"));
            Parent gameRoot = loader.load();

            Controleur controleur = loader.getController();

            Scene gameScene = new Scene(gameRoot);
            gameScene.getStylesheets().add(getClass().getResource("/com/example/sae_zeldalike/vue1.css").toExternalForm());

            // Obtenez la fenêtre actuelle de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(gameScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void quitter(ActionEvent event){

        Platform.exit();

    }

    @FXML
    private void credit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuJeu/credit.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().addAll(getClass().getResource("/MenuJeu/MenuJeu.css").toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Crédits");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void aide(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuJeu/aide.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().addAll(getClass().getResource("/MenuJeu/MenuJeu.css").toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Crédits");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    private void reglesDuJeu() {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Règles du jeu");
//        alert.setHeaderText(null);
//        alert.setContentText("Les règles de notre jeu sont les suivantes : ...");
//        alert.showAndWait();
//    }


}
