package com.example.sae_zeldalike.Vue.Item;

import com.example.sae_zeldalike.modele.Item.CoeurRouge;
import com.example.sae_zeldalike.modele.Item.Item;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VueCoeurRouge extends VueItem{

    private Image coeurVide,coeurDemi,coeurPlein;
    public VueCoeurRouge(Pane pane, Item item){
        super(pane, item);
        initialiserItem();
        if (item instanceof CoeurRouge){
            if (((CoeurRouge) item).getPointDeVie()==10){
                spriteItem.setImage(coeurPlein);
            }else if (((CoeurRouge) item).getPointDeVie()>0){
                spriteItem.setImage(coeurDemi);
            }else {
                spriteItem.setImage(coeurVide);
            }
        }
        creerOmbre(pane);
        pane.getChildren().add(spriteItem);


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
        this.coeurVide= new Image("file:src/main/resources/com/example/sae_zeldalike/ATH/CoeurRouge_Vide.png");
        this.coeurDemi = new Image("file:src/main/resources/com/example/sae_zeldalike/ATH/CoeurRouge_Demi.png");
        this.coeurPlein = new Image("file:src/main/resources/com/example/sae_zeldalike/ATH/CoeurRouge_Plein.png");
    }

    @Override
    public void animationItem() {

    }


    @Override
    public Image getImagePrincipale() {

            if (((CoeurRouge) item).getPointDeVie()==10){
                return coeurPlein;
            }else if (((CoeurRouge) item).getPointDeVie()>0){
                return coeurDemi;
            }else {
                return coeurVide;
            }
    }


}
