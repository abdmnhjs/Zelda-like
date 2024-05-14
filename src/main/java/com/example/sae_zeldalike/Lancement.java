package com.example.sae_zeldalike;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Lancement extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sae_zeldalike/nterface.fxml"));
        Parent root = loader.load();


        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}