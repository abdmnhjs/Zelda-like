package com.example.sae_zeldalike;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Lancement extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sae_zeldalike/vue.fxml"));
        Parent root = loader.load();
        Controleur controleur = loader.getController();
        Scene scene = new Scene(root, 900, 600);
        scene.setOnKeyPressed(e -> {
            KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", e.getCode(), e.isShiftDown(), e.isControlDown(), e.isAltDown(), e.isMetaDown());
            controleur.getClavier().handle(keyEvent);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}