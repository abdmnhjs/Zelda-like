package com.example.sae_zeldalike.Vue.Item;

import com.example.sae_zeldalike.modele.Item.CoeurBleu;
import com.example.sae_zeldalike.modele.Item.CoeurRouge;
import com.example.sae_zeldalike.modele.Item.Item;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VueCoeurBleu extends VueItem{

    private Image coeurPlein,coeurDemi,coeurVide;
    public VueCoeurBleu(Pane pane, Item item) {
        super(pane, item);
        initialiserItem();
        if (item instanceof CoeurBleu){
            spriteItem.setImage(coeurPlein);

        }
        creerOmbre(pane);
        pane.getChildren().add(spriteItem);

        //Binding des coordonnées de l'item
        spriteItem.translateXProperty().bind(item.getPositionXProperty());
        spriteItem.translateYProperty().bind(item.getPositionYProperty());

    }

    @Override
    protected void creerOmbre(Pane pane) {
        ombre = new Circle((item.getLargeur()/8));
        ombre.setFill(Color.TRANSPARENT);
        ombre.setId("O"+item.getId());
        pane.getChildren().add(ombre);
        ombre.centerXProperty().bind(item.getPositionXProperty().add((item.getLargeur()/2)));
        ombre.centerYProperty().bind(item.getPositionYProperty().add((item.getLongueur()/2)));
    }

    @Override
    protected void initialiserItem() {
        coeurPlein=new Image("file:src/main/resources/com/example/sae_zeldalike/ATH/CoeurBleu_Plein.png");
        coeurDemi=new Image("file:src/main/resources/com/example/sae_zeldalike/ATH/CoeurBleu_Demi.png");
        coeurVide=new Image("file:src/main/resources/com/example/sae_zeldalike/ATH/CoeurBleu_Vide.png");
    }

    @Override
    public void animationItem() {

    }

    @Override
    public Image getImagePrincipale() {
        if (((CoeurBleu) item).getVieAdditionelle()==10){
            return coeurPlein;
        }else if (((CoeurBleu) item).getVieAdditionelle()>0){
            return coeurDemi;
        }else {
            return coeurVide;
        }
    }
}
