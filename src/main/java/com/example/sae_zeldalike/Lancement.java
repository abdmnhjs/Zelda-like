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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vue1.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root,1088,800);
        scene.getStylesheets().add(getClass().getResource("vue1.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Zelda");
        stage.show();
    }
}