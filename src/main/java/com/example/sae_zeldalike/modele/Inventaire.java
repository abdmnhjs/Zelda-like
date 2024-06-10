package com.example.sae_zeldalike.modele;

import com.example.sae_zeldalike.modele.Item.Stockable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Inventaire {

    private IntegerProperty indiceChangement;
    private Stockable[] inventaire;
    private IntegerProperty caseActuel;

    public Inventaire(){
        this.indiceChangement=new SimpleIntegerProperty(-1);
        this.inventaire= new Stockable[6];
        this.caseActuel = new SimpleIntegerProperty(0);
    }

    public int getIndiceChangement() {
        return indiceChangement.getValue();
    }

    public IntegerProperty getIndiceChangementProperty() {
        return indiceChangement;
    }

    public void setIndiceChangement(int indiceChangement) {
        this.indiceChangement.setValue(indiceChangement);
    }

    public Stockable[] getInventaire() {
        return inventaire;
    }

    public Stockable getInventaireCase1(){
        return inventaire[0];
    }
    public Stockable getInventaireCase2(){
        return inventaire[1];
    }
    public Stockable getInventaireCase3(){
        return inventaire[2];
    }
    public Stockable getInventaireCase4(){
        return inventaire[3];
    }

    public Stockable getInventaireCase5(){
        return inventaire[4];
    }

    public Stockable getInventaireCase6(){
        return inventaire[5];
    }

    public Stockable getInventaireCaseActuel(){
        return inventaire[getCaseActuel()];
    }

    public void modifieStockableInventaire(Stockable stockable){
        inventaire[getCaseActuel()]=stockable;
    }

    public ArrayList<Integer> connaitreIndiceCaseVide(){

        ArrayList<Integer>indice = new ArrayList<>();
        for (int i=0;i<inventaire.length;i++) {
            if (inventaire[i] == null) {
                indice.add(i);
            }
        }
        return indice;
    }

    public void modifieCaseInventaireAvecIndiceDonnee(int indice,Stockable stockable){
        if (indice>=0 && indice<inventaire.length){
            inventaire[indice]=stockable;
        }
    }

    public IntegerProperty getCaseActuelProperty(){
        return caseActuel;
    }

    public int getCaseActuel() {
        return caseActuel.getValue();
    }

    public void setCaseActuel(int numero) {
        if (numero>=0 && numero< inventaire.length) {
            this.caseActuel.setValue(numero);
        }
    }


}
