package com.example.sae_zeldalike.Vue.Item.Arme;

import com.example.sae_zeldalike.Vue.Item.VueItem;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arme;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VueArc extends VueItem {

    private Image arcG,arcH,arcD,arcB;
    private String directionPrecedente;


    public VueArc(Pane pane, Item item) {
        super(pane, item);
        initialiserItem();
        spriteItem.setImage(arcD);
        creerOmbre(pane);
        directionPrecedente="";

        if (((Arme)item).estPorterParPerso()){

            ((Arme)item).getDirectionProperty().bind(((Arme) item).getPersonnage().getDirectionProperty());
            System.out.println(((Arme) item).getDirection());

        }
        pane.getChildren().add(spriteItem);
    }

    @Override
    protected void creerOmbre(Pane pane) {
        ombre = new Circle((item.getLargeur()/4)-2);
        ombre.setFill(Color.TRANSPARENT);
        ombre.setId("O"+item.getId());
        pane.getChildren().add(ombre);
        ombre.centerXProperty().bind(item.getPositionXProperty().add((item.getLargeur()/2)));
        ombre.centerYProperty().bind(item.getPositionYProperty().add(23));
    }

    @Override
    protected void initialiserItem() {

        this.arcB = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Arme/Arc/arc_DOWN.png");
        this.arcD = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Arme/Arc/arc_RIGHT.png");
        this.arcG = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Arme/Arc/arc_LEFT.png");
        this.arcH = new Image("file:src/main/resources/com/example/sae_zeldalike/Item/Arme/Arc/arc_UP.png");
    }

    @Override
    public void animationItem() {
        if (((Arme) item).estPorterParPerso()) {
            String direction = ((Arme)item).getDirection();

            if (direction.equals(directionPrecedente)) {
                return;
            }
            directionPrecedente = direction;

            switch (direction) {
                case "UP":
                case "Inactif_UP":
                    item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty());
                    item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty().subtract(((Arme) item).getPersonnage().getLongueur()/1.5));
                    spriteItem.setImage(arcH);
                    break;
                case "DOWN":
                case "Inactif_DOWN":
                    item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty());
                    item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty().add(((Arme) item).getPersonnage().getLongueur()/1.5));
                    spriteItem.setImage(arcB);
                    break;
                case "RIGHT":
                case "Inactif_RIGHT":
                    item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty().add(((Arme) item).getPersonnage().getLargeur()/1.5));
                    item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty());
                    spriteItem.setImage(arcD);
                    break;
                case "LEFT":
                case "Inactif_LEFT":
                    item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty().subtract(((Arme) item).getPersonnage().getLargeur()/1.5));
                    item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty());
                    spriteItem.setImage(arcG);
                    break;
            }
        }
    }

    @Override
    public Image getImagePrincipale() {
        return arcD;
    }
}
