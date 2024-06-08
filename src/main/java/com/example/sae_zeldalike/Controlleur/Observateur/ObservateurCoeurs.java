package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.modele.Personnage.Link;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ObservateurCoeurs implements ChangeListener<Number> {

    private HBox emplacementCoeurs;
    private Link link;

    public ObservateurCoeurs(HBox emplacementCoeurs, Link link) {
        this.emplacementCoeurs = emplacementCoeurs;
        this.link = link;
        mettreAJourCoeurs(link.getPointVie(), link.getPointDeVieAdditionelle());
    }

    @Override
    public void changed(ObservableValue<? extends Number> obs, Number old, Number nouv) {
        mettreAJourCoeurs(link.getPointVie(), link.getPointDeVieAdditionelle());
    }

    private void mettreAJourCoeurs(int pointsDeVie, int pointsDeVieAdditionnelle) {
        emplacementCoeurs.getChildren().clear();

        int totalPv = link.getPointDeVieMax();

        int coeursBleusPlein = pointsDeVieAdditionnelle / 10;
        int resteBleu = pointsDeVieAdditionnelle % 10;

        int nombreCoeursPlein = pointsDeVie / 10;
        int reste = pointsDeVie % 10;

        for (int i = 0; i < nombreCoeursPlein && i < totalPv / 10; i++) {
            emplacementCoeurs.getChildren().add(creerImageCoeur("CoeurRouge_Plein.png"));
        }

        if (reste > 0 && nombreCoeursPlein < totalPv / 10) {
            emplacementCoeurs.getChildren().add(creerImageCoeur("CoeurRouge_Demi.png"));
        }

        int nombreCoeursVides = (totalPv / 10) - nombreCoeursPlein;
        if (reste > 0) {
            nombreCoeursVides -= 1;
        }
        for (int i = 0; i < nombreCoeursVides; i++) {
            emplacementCoeurs.getChildren().add(creerImageCoeur("CoeurRouge_Vide.png"));
        }

        for (int i = 0; i < coeursBleusPlein; i++) {
            emplacementCoeurs.getChildren().add(creerImageCoeur("CoeurBleu_Plein.png"));
        }
        if (resteBleu > 0) {
            emplacementCoeurs.getChildren().add(creerImageCoeur("CoeurBleu_Demi.png"));
        }

    }

    private ImageView creerImageCoeur(String chemin) {
        Image image = new Image(getClass().getResourceAsStream("/com/example/sae_zeldalike/ATH/" + chemin));
        ImageView coeur = new ImageView(image);
        coeur.setFitWidth(25);
        coeur.setFitHeight(25);
        return coeur;
    }
}
