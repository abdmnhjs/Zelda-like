package com.example.sae_zeldalike.Controlleur.Observateur;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class ObservateurCaseInventaire implements ChangeListener<Number> {

    @FXML
    private StackPane emplacement1;
    @FXML
    private StackPane emplacement2;
    @FXML
    private StackPane emplacement3;

    public ObservateurCaseInventaire(StackPane emplacement1, StackPane emplacement2, StackPane emplacement3) {
        this.emplacement1 = emplacement1;
        this.emplacement2 = emplacement2;
        this.emplacement3 = emplacement3;
        changementCaseActuel(0);
    }

    @Override
    public void changed(ObservableValue<? extends Number> obs, Number old, Number nouv) {

        changementCaseActuel(nouv.intValue());
    }

    private void changementCaseActuel(int numeroCaseActuel) {
        emplacement1.getStyleClass().remove("caseInventaireActuel");
        emplacement2.getStyleClass().remove("caseInventaireActuel");
        emplacement3.getStyleClass().remove("caseInventaireActuel");

        emplacement1.setTranslateY(0);
        emplacement2.setTranslateY(0);
        emplacement3.setTranslateY(0);

        switch (numeroCaseActuel) {
            case 0:
                emplacement1.getStyleClass().add("caseInventaireActuel");
                emplacement1.setTranslateY(emplacement1.getTranslateY()-20);
                break;
            case 1:
                emplacement2.getStyleClass().add("caseInventaireActuel");
                emplacement2.setTranslateY(emplacement2.getTranslateY()-20);
                break;
            case 2:
                emplacement3.getStyleClass().add("caseInventaireActuel");
                emplacement3.setTranslateY(emplacement3.getTranslateY()-20);
                break;
            default:
                break;
        }
    }
}
