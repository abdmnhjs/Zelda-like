package com.example.sae_zeldalike.Controlleur.Observateur;

import com.example.sae_zeldalike.Vue.Personnage.VueEnnemi1;
import com.example.sae_zeldalike.Vue.Personnage.VuePersonnage;
import com.example.sae_zeldalike.modele.Item.Stockable;
import com.example.sae_zeldalike.modele.Personnage.Ennemi1;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class ObservateurInventaire implements ListChangeListener<Stockable> {



    private Pane pane;
    private ImageView case1; ;

    private ImageView case2;

    private ImageView case3;

    public ObservateurInventaire(ImageView case1, ImageView case2, ImageView case3) {

       this.case1 = case1;
       this.case2 = case2;
       this.case3= case3;

    }

    @Override
    public void onChanged(Change<? extends Stockable> change) {
        System.out.println("hhhhhhhhhhhhhhh" + change.getList().get(0));
        case1.setImage(change.getList().get(0).getItem().getImage());
//        case1.setImage(new Image("file:src/main/resources/com/example/sae_zeldalike/ATH/CoeurBleu_Plein.png"));

    }

}
