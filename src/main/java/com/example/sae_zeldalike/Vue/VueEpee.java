package com.example.sae_zeldalike.Vue;

import com.example.sae_zeldalike.Vue.Item.VueItem;
import com.example.sae_zeldalike.modele.Item.Item;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Arme;
import com.example.sae_zeldalike.modele.Item.StockableDansInventaire.Arme.Epée;
import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class VueEpee extends VueItem {

    private Image epeeH,epeeB,epeeG,epeeD;

    public VueEpee(Pane pane,Item item) {
        super(pane,item);
        initialiserItem();
        spriteItem.setImage(epeeH);
        creerOmbre(pane);


        if (((Arme)item).estPorterParPerso()){

            ((Arme)item).getDirectionProperty().bind(((Arme) item).getPersonnage().getDirectionProperty());
            System.out.println(((Arme) item).getDirection());



//            creerEpee();
        }
        pane.getChildren().add(spriteItem);

    }

    public void creerEpee() {
        if (((Arme) item).estPorterParPerso()) {
            Personnage perso = ((Arme) item).getPersonnage();
            if (perso.getDirection().equals("UP") || perso.getDirection().equals("Inactif_UP")) {

                spriteItem.setImage(epeeH);
                this.spriteItem.translateXProperty().bind(((Arme) item).getPersonnage().getPositionXProperty().add(((Arme) item).getPersonnage().getLargeur() / 7));
                this.spriteItem.translateYProperty().bind(((Arme) item).getPersonnage().getPositionXProperty().add(((Arme) item).getPersonnage().getLargeur()));

            }
            if (perso.getDirection().equals("DOWN")|| perso.getDirection().equals("Inactif_DOWN")) {
                spriteItem.setImage(epeeB);
                this.spriteItem.translateXProperty().bind(((Arme) item).getPersonnage().getPositionXProperty().add(((Arme) item).getPersonnage().getLargeur() / 7));
                this.spriteItem.translateYProperty().bind(((Arme) item).getPersonnage().getPositionXProperty().add(((Arme) item).getPersonnage().getLargeur()));

            }
            if (perso.getDirection().equals("RIGHT")|| perso.getDirection().equals("Inactif_RIGHT")) {
                spriteItem.setImage(epeeD);
                this.spriteItem.translateXProperty().bind(((Arme) item).getPersonnage().getPositionXProperty().add(32));
                this.spriteItem.translateXProperty().bind(((Arme) item).getPersonnage().getPositionXProperty().add(16));
            }
            if (perso.getDirection().equals("LEFT")|| perso.getDirection().equals("Inactif_LEFT")) {
                spriteItem.setImage(epeeG);
                this.spriteItem.translateXProperty().bind(((Arme) item).getPersonnage().getPositionXProperty().add(32));
                this.spriteItem.translateXProperty().bind(((Arme) item).getPersonnage().getPositionXProperty().add(16));

            }
            this.spriteItem.setFitHeight(this.item.getLongueur());
            this.spriteItem.setFitWidth(this.item.getLargeur());
        }
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
            Personnage perso = ((Arme) item).getPersonnage();
            if (perso.getDirection().equals("UP")
                    || perso.getDirection().equals("Inactif_UP")
            ) {
                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty().subtract(item.getLargeur()/6));
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty().subtract(((Arme) item).getPersonnage().getLongueur()/2));
                spriteItem.setImage(epeeH);

            }
            if (perso.getDirection().equals("DOWN")
                    || perso.getDirection().equals("Inactif_DOWN")
            ) {
                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty().subtract(item.getLargeur()/6));
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty().add(((Arme) item).getPersonnage().getLongueur()/2));

                spriteItem.setImage(epeeB);

            }
            if (perso.getDirection().equals("RIGHT")
                    || perso.getDirection().equals("Inactif_RIGHT")
            ) {
                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty().add(((Arme) item).getPersonnage().getLargeur()/4));
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty());

                spriteItem.setImage(epeeD);
            }
            if (perso.getDirection().equals("LEFT")
                    || perso.getDirection().equals("Inactif_LEFT")
            ) {
                item.getPositionXProperty().bind(((Arme)item).getPersonnage().getPositionXProperty().subtract(((Arme) item).getPersonnage().getLargeur()));
                item.getPositionYProperty().bind(((Arme)item).getPersonnage().getPositionYProperty());
                spriteItem.setImage(epeeG);

            }
            this.spriteItem.setFitHeight(this.item.getLongueur());
            this.spriteItem.setFitWidth(this.item.getLargeur());
        }

    }

    @Override
    public Image getImagePrincipale() {
        return epeeH;
    }
}
