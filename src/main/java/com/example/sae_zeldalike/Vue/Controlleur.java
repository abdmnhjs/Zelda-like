package com.example.sae_zeldalike.Vue;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controlleur implements Initializable {

    @FXML
    private ImageView player;
    @FXML
    private Label pieceCounterLabel;
    private int compteurPiece=0;
    private static int currentFrameIndex = -1;
    private List<ImageView> pieceImageViews = new ArrayList<>();

    private int[] data; // Déclaration de la variable data
    private Pane root; // Déclaration de la variable root
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
// Initialiser l'image du joueur
        Image playerImage = new Image(getClass().getResourceAsStream("/b4.png"));
        player.setImage(playerImage);

        // Ajouter un gestionnaire d'événements pour détecter lorsque la scène est définie
        player.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                Platform.runLater(() -> {
                    try {
                        Thread.sleep(1); // Attendre 1 milliseconde pour laisser le temps à la scène de se définir
                        checkSceneSize();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    private void checkSceneSize() {
        Platform.runLater(() -> {
            Scene scene = player.getScene();
            if (scene != null && scene.getRoot() != null) {
                double sceneWidth = scene.getWidth();
                double sceneHeight = scene.getHeight();
                if (sceneWidth != 0 && sceneHeight != 0) {
                    loadTilemap("src/main/resources/terrain2.json", (Pane) scene.getRoot());
                    // Focus sur l'image du joueur pour recevoir les événements de touche
                    player.requestFocus();
                } else {
                    // Si les dimensions ne sont pas encore initialisées, planifier une nouvelle vérification
                    checkSceneSize();
                }
            }
        });
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        // Gérer les événements des touches
        double newX = player.getX();
        double newY = player.getY();

        double tileSize = 32; // Taille des tuiles
        int playerTileX = (int) ((newX + player.getFitWidth() / 2) / tileSize); // Utiliser le centre de l'image pour les calculs de position
        int playerTileY = (int) ((newY + player.getFitHeight() / 2) / tileSize); // Utiliser le centre de l'image pour les calculs de position

        switch (event.getCode()) {
            case UP:
                playerTileY -= 1;         animatePlayer();

                break;
            case DOWN:
                playerTileY += 1;         animatePlayer1();

                break;
            case LEFT:
                playerTileX -= 1;         animatePlayer2();

                break;
            case RIGHT:
                playerTileX += 1;         animatePlayer3();

                break;
        }

        // Vérifier si la nouvelle position est valide (pas en dehors des limites de la carte)
        if (playerTileX >= 0 && playerTileX < 34 && playerTileY >= 0 && playerTileY < 17) {
            int newTileId = data[playerTileY * 34 + playerTileX];

            if (newTileId != 54) { // Vérifier si la future tuile n'est pas un mur (ID 54)
                newX = playerTileX * tileSize;
                newY = playerTileY * tileSize;
                player.setX(newX);
                player.setY(newY);
                // Mettre l'image du joueur en premier plan
                player.toFront();
            }

            // Vérifiez si la future tuile contient une pièce (ID 55)
            if (newTileId == 55) {
                compteurPiece++; // Incrémente le compteur de pièces
                pieceCounterLabel.setText("Nombre de pièces : " + compteurPiece);

                // Remplacer la valeur 55 par 11 pour indiquer que la pièce a été ramassée
                data[playerTileY * 34 + playerTileX] = 11;
                Image neutralImage = getImageForTile(11);
                ImageView neutralImageView = new ImageView(neutralImage);
                neutralImageView.setX(newX);
                neutralImageView.setY(newY);
                neutralImageView.setFitWidth(tileSize);
                neutralImageView.setFitHeight(tileSize);
                root.getChildren().add(neutralImageView);
                player.toFront();

                // Recherchez l'ImageView correspondant à cette pièce dans la liste et retirez-le de la scène
                for (ImageView imageView : pieceImageViews) {
                    if (imageView.getX() == newX && imageView.getY() == newY) {
                        root.getChildren().remove(imageView);
                        pieceImageViews.remove(imageView);
                        break; // Sortez de la boucle dès que la pièce est trouvée et retirée
                    }
                }
            }
        } else {
            System.out.println("Le joueur est en dehors des limites de la carte.");
        }
    }

        @FXML
        private void animatePlayer() {
            if (currentFrameIndex == -1 || currentFrameIndex == 0) {
                player.setImage(new Image(getClass().getResourceAsStream("/b8.png")));
            } else {
                player.setImage(new Image(getClass().getResourceAsStream("/b9.png")));
            }

            currentFrameIndex = (currentFrameIndex + 1) % 2;
        }

        @FXML
        private void animatePlayer1() {
            if (currentFrameIndex == -1 || currentFrameIndex == 0) {
                player.setImage(new Image(getClass().getResourceAsStream("/b11.png")));
            } else {
                player.setImage(new Image(getClass().getResourceAsStream("/b12.png")));
            }

            currentFrameIndex = (currentFrameIndex + 1) % 2;
        }

        @FXML
        private void animatePlayer2() {
            if (currentFrameIndex == -1 || currentFrameIndex == 0) {
                player.setImage(new Image(getClass().getResourceAsStream("/b40.png")));
            } else {
                player.setImage(new Image(getClass().getResourceAsStream("/b41.png")));
            }

            currentFrameIndex = (currentFrameIndex + 1) % 2;
        }

        @FXML
        private void animatePlayer3() {
            if (currentFrameIndex == -1 || currentFrameIndex == 0) {
                player.setImage(new Image(getClass().getResourceAsStream("/b4.png")));
            } else {
                player.setImage(new Image(getClass().getResourceAsStream("/b3.png")));
            }

            currentFrameIndex = (currentFrameIndex + 1) % 2;
        }

    private void loadTilemap(String filename, Pane root) {
        try {
            data = getData(filename); // Initialisation de la variable data
            this.root = root; // Initialisation de la variable root

            double tileWidth = 32; // Taille des tuiles
            double tileHeight = 32; // Taille des tuiles

            for (int i = 0; i < data.length; i++) {
                int tileId = data[i];
                double col = i % 34; // Calculer la colonne en fonction de l'index et de la largeur de la carte
                double row = i / 34; // Calculer la ligne en fonction de l'index et de la largeur de la carte
                double x = col * tileWidth;
                double y = row * tileHeight;

                if (tileId != 0) {
                    Image tileImage = getImageForTile(tileId);
                    if (tileImage != null) {
                        ImageView imageView = new ImageView(tileImage);
                        imageView.setX(x);
                        imageView.setY(y);
                        imageView.setFitWidth(tileWidth);
                        imageView.setFitHeight(tileHeight);
                        root.getChildren().add(imageView);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image getImageForTile(int tileId) {
        String imagePath;
        if (tileId == 11) {
            imagePath = "/11.png";
        } else {
            imagePath = "/" + tileId + ".png";
        }
        try {
            return new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imagePath);
            return null;
        }
    }

    private int[] getData(String filename) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        JSONObject json = new JSONObject(content);
        JSONArray data = json.getJSONArray("data");
        // Convertir JSONArray en tableau d'entiers
        int[] dataArray = new int[data.length()];
        for (int i = 0; i < data.length(); i++) {
            dataArray[i] = data.getInt(i);
        }
        return dataArray;
    }
}
