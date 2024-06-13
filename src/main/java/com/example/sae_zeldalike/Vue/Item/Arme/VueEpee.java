package com.example.sae_zeldalike.Vue.Item.Arme;

import com.example.sae_zeldalike.Vue.Item.VueItem;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arme;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class VueEpee extends VueItem {

    private Image epeeH,epeeB,epeeG,epeeD;
    private String directionPrecedente;

    public VueEpee(Pane pane,Item item) {
        super(pane,item);
        initialiserItem();
        spriteItem.setImage(epeeH);
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
        epeeB = new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-bas.png");
        epeeG = new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-gauche.png");
        epeeH = new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-haut.png");
        epeeD = new Image("file:src/main/resources/com/example/sae_zeldalike/Epee/epee-droite.png");
    }

    @Override
    public void animationItem() {
        if (((Arme) item).estPorterParPerso()) {
            String direction = ((Arme)item).getDirection();

            if (direction.equals(directionPrecedente)) {
                return;
            }
            directionPrecedente = direction;

            if (((Arme)item).getDirection().equals("UP")
                    || ((Arme)item).getDirection().equals("Inactif_UP")
            ) {

                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty());
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty().subtract(((Arme) item).getPersonnage().getLongueur()/1.5));
                spriteItem.setImage(epeeH);

            }
            if (((Arme)item).getDirection().equals("DOWN")
                    || ((Arme)item).getDirection().equals("Inactif_DOWN")
            ) {
                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty());
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty().add(((Arme) item).getPersonnage().getLongueur()/1.5));
                spriteItem.setImage(epeeB);
            }
            if (((Arme)item).getDirection().equals("RIGHT")
                    || ((Arme)item).getDirection().equals("Inactif_RIGHT")
            ) {

                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty().add(((Arme) item).getPersonnage().getLargeur()/1.5));
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty());

                spriteItem.setImage(epeeD);
            }
            if (((Arme)item).getDirection().equals("LEFT")
                    || ((Arme)item).getDirection().equals("Inactif_LEFT")
            ) {
                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty().subtract(((Arme) item).getPersonnage().getLargeur()/1.5));
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty());
                spriteItem.setImage(epeeG);

            }
        }

    }

    @Override
    public Image getImagePrincipale() {
        return epeeH;
    }
}
