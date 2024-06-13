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

    public VueArc(Pane pane, Item item) {
        super(pane, item);
        initialiserItem();
        spriteItem.setImage(arcD);
        creerOmbre(pane);

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

            if (((Arme)item).getDirection().equals("UP")
                    || ((Arme)item).getDirection().equals("Inactif_UP")
            ) {

                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty());
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty().subtract(((Arme) item).getPersonnage().getLongueur()/1.5));

                spriteItem.setImage(arcH);

            }
            if (((Arme)item).getDirection().equals("DOWN")
                    || ((Arme)item).getDirection().equals("Inactif_DOWN")
            ) {
                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty());
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty().add(((Arme) item).getPersonnage().getLongueur()/1.5));

                spriteItem.setImage(arcB);
            }
            if (((Arme)item).getDirection().equals("RIGHT")
                    || ((Arme)item).getDirection().equals("Inactif_RIGHT")
            ) {

                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty().add(((Arme) item).getPersonnage().getLargeur()/1.5));
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty());

                spriteItem.setImage(arcD);
            }
            if (((Arme)item).getDirection().equals("LEFT")
                    || ((Arme)item).getDirection().equals("Inactif_LEFT")
            ) {
                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty().subtract(((Arme) item).getPersonnage().getLargeur()/1.5));
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty());

                spriteItem.setImage(arcG);

            }
        }
    }

    @Override
    public Image getImagePrincipale() {
        return arcD;
    }
}
