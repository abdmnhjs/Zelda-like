package com.example.sae_zeldalike.Vue.Item;

import com.example.sae_zeldalike.modele.Item.Item;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VuePoison extends VueItem{
    private Image poison;

    public VuePoison(Pane pane, Item item) {
        super(pane, item);
        initialiserItem();
        spriteItem.setImage(poison);
        creerOmbre(pane);
        pane.getChildren().add(spriteItem);
    }
    @Override
    protected void creerOmbre(Pane pane) {
        ombre = new Circle((item.getLargeur()/4)-2);
        ombre.setFill(Color.DARKSLATEGRAY);
        ombre.setId("O"+item.getId());
        pane.getChildren().add(ombre);
        ombre.centerXProperty().bind(item.getPositionXProperty().add((item.getLargeur()/2)));
        ombre.centerYProperty().bind(item.getPositionYProperty().add(23));
    }

    @Override
    protected void initialiserItem() {
        poison = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Poison/poison.png");

    }

    @Override
    public void animationItem() {

    }


    @Override
    public Image getImagePrincipale() {
        return poison;
    }
}
