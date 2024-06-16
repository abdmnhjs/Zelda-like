package com.example.sae_zeldalike.Controlleur.MenuJeu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Aide {

    @FXML
    private TableView<Commande> tableView;
    @FXML
    private TableColumn<Commande, String> toucheColumn;
    @FXML
    private TableColumn<Commande, String> actionColumn;

    @FXML
    private void initialize() {
        toucheColumn.setCellValueFactory(new PropertyValueFactory<>("touche"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));

        tableView.setItems(getCommandes());
    }

    private ObservableList<Commande> getCommandes() {
        ObservableList<Commande> commandes = FXCollections.observableArrayList();
        commandes.add(new Commande("Z", "Aller en haut"));
        commandes.add(new Commande("Q", "Aller à gauche"));
        commandes.add(new Commande("S", "Aller en bas"));
        commandes.add(new Commande("D", "Aller à droite"));
        commandes.add(new Commande("Flèche du haut", "Attaquer/Tirer en direction vers le haut"));
        commandes.add(new Commande("Flèche de gauche", "Attaquer/Tirer en direction vers la gauche"));
        commandes.add(new Commande("Flèche du bas", "Attaquer/Tirer en direction vers le bas"));
        commandes.add(new Commande("Flèche de droite", "Attaquer/Tirer en direction vers la droite"));
        commandes.add(new Commande("H", "Tenir / Pousser /Lâcher objet déplaçable"));
        commandes.add(new Commande("Y", "Item suivant"));
        commandes.add(new Commande("U", "Item précédent"));
        commandes.add(new Commande("I", "Utiliser/Equiper objet de l’inventaire"));
        commandes.add(new Commande("J", "Ramasser / Interagir / Passer le texte / Valider"));
        commandes.add(new Commande("Echap", "Menu Pause"));
        return commandes;
    }
}
