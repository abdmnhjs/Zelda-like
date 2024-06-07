package com.example.sae_zeldalike.Controlleur.Observateur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ObservateurCoeurs implements ChangeListener<Number> {

    private HBox emplacementCoeurs;

    public ObservateurCoeurs(HBox emplacementCoeurs,int pvInitial) {
        this.emplacementCoeurs = emplacementCoeurs;
        mettreAJourCoeurs(pvInitial);
    }


    @Override
    public void changed(ObservableValue<? extends Number> obs, Number old, Number nouv) {
        mettreAJourCoeurs(nouv.intValue());
    }


    private void mettreAJourCoeurs(int pointsDeVie) {
        emplacementCoeurs.getChildren().clear();
        int nombreCoeursPlein = pointsDeVie / 10;
        int reste = pointsDeVie % 10;

        for (int i = 0; i < nombreCoeursPlein; i++) {
            emplacementCoeurs.getChildren().add(creerImageCoeur("CoeurRouge_Plein.png"));
        }

        if (reste > 0) {
            emplacementCoeurs.getChildren().add(creerImageCoeur("CoeurRouge_Demi.png"));
        }

        int nombreCoeursVides;
        if (reste > 0) {
            nombreCoeursVides = 10 - nombreCoeursPlein - 1;
        } else {
            nombreCoeursVides = 10 - nombreCoeursPlein;
        }
        for (int i = 0; i < nombreCoeursVides; i++) {
            emplacementCoeurs.getChildren().add(creerImageCoeur("CoeurRouge_Vide.png"));
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
