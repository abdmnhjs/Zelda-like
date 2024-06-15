package com.example.sae_zeldalike.Vue.Personnage;

import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class VueEnnemi1 extends VuePersonnage{

    private Image spritePerso1,spritePerso2,spritePerso3;
    private boolean degresif = true;
    private Rectangle vie;
    private Rectangle arrPlanVie;

    public VueEnnemi1 (Pane pane, Personnage personnage) {
        super(pane, personnage);
        initialiser();
        this.spritePersonnage.setImage(spritePerso1);
        creerBarreDeVie(pane);
        creerOmbre(pane);
        pane.getChildren().add(spritePersonnage);
        spritePersonnage.translateXProperty().bind(personnage.getPositionXProperty());
        spritePersonnage.translateYProperty().bind(personnage.getPositionYProperty());
        vie.widthProperty().addListener((obs,old,nouv)->mettreAJourCouleurBarreDeVie());
    }

    private void initialiser(){
        spritePerso1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Chauve-souris/1.png");
        spritePerso2 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Chauve-souris/2.png");
        spritePerso3 = new Image("file:src/main/resources/com/example/sae_zeldalike/Personnage/Chauve-souris/3.png");
    }

    private void creerBarreDeVie(Pane pane){
        arrPlanVie = new Rectangle();
        arrPlanVie.setFill(Color.GREY);
        arrPlanVie.setWidth((personnage.getPointVie()+10)/2);
        arrPlanVie.setHeight(9);
        arrPlanVie.setId("BCKGL"+personnage.getId());
        arrPlanVie.setArcHeight(20);
        arrPlanVie.setArcWidth(10);
        pane.getChildren().add(arrPlanVie);
        vie = new Rectangle();
        vie.setFill(Color.GREEN);
        vie.setWidth((personnage.getPointVie())/2);
        vie.setHeight(5);
        vie.setId("L"+personnage.getId());
        vie.setArcHeight(20);
        vie.setArcWidth(10);
        pane.getChildren().add(vie);
        arrPlanVie.translateXProperty().bind(personnage.getPositionXProperty().add((personnage.getLargeur()-arrPlanVie.getWidth())/2));
        arrPlanVie.translateYProperty().bind(personnage.getPositionYProperty().add(((personnage.getLongueur()-arrPlanVie.getHeight())/2)-personnage.getLongueur()/2));
        vie.translateXProperty().bind(personnage.getPositionXProperty().add(((personnage.getLargeur()-vie.getWidth())/2)));
        vie.translateYProperty().bind(personnage.getPositionYProperty().add(((personnage.getLongueur()-vie.getHeight())/2)-personnage.getLongueur()/2));
        vie.widthProperty().bind(personnage.getPointVieProperty().divide(2));
    }

    private void mettreAJourCouleurBarreDeVie() {
        double largeurActuelle = vie.getWidth();
        double largeurMaximale = arrPlanVie.getWidth() - 5;

        if (largeurActuelle >= largeurMaximale * 0.9) {
            vie.setFill(Color.GREEN);
        }else if (largeurActuelle>=largeurMaximale*0.7){
            vie.setFill(Color.YELLOWGREEN);
        }else if (largeurActuelle >= largeurMaximale * 0.5) {
            vie.setFill(Color.ORANGERED);
        }else if (largeurActuelle< largeurMaximale*0.35){
            vie.setFill(Color.RED);
        }
    }

    @Override
    protected void creerOmbre(Pane pane) {
        ombre = new Circle((personnage.getLargeur()/2.5));
        ombre.setFill(Color.RED);
        ombre.setId("O"+personnage.getId());

        pane.getChildren().add(this.ombre);

        ombre.centerXProperty().bind(spritePersonnage.translateXProperty().add(personnage.getLargeur()/2));
        ombre.centerYProperty().bind(spritePersonnage.translateYProperty().add(personnage.getLongueur()-5));
    }

    public void animation() {
        if (numeroImagePersonnage == 1) {
            if (degresif) {
                setNumeroImagePersonnage(2);
                spritePersonnage.setImage(spritePerso1);
            } else {
                setNumeroImagePersonnage(2);
                spritePersonnage.setImage(spritePerso1);
                degresif = true;
            }
        } else if (numeroImagePersonnage == 2) {
            if (degresif) {
                setNumeroImagePersonnage(3);
                spritePersonnage.setImage(spritePerso2);
            } else {
                setNumeroImagePersonnage(1);
                spritePersonnage.setImage(spritePerso2);
            }
        } else if (numeroImagePersonnage == 3) {
            if (degresif) {
                setNumeroImagePersonnage(2);
                spritePersonnage.setImage(spritePerso3);
                degresif = false;
            }
        }
    }
}
