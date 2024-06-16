package com.example.sae_zeldalike;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lancement extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuJeu/MenuJeu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1088, 800);
        scene.getStylesheets().addAll(getClass().getResource("/MenuJeu/MenuJeu.css").toExternalForm());

        stage.setScene(scene);

        stage.setTitle("Zelda");
        stage.show();
    }
}