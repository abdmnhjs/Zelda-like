package com.example.sae_zeldalike.Vue.Personnage;

import com.example.sae_zeldalike.modele.Personnage.Personnage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public abstract class VuePersonnage {

    protected Personnage personnage;
    protected ImageView spritePersonnage;
    protected int numeroImagePersonnage;
    protected Circle ombre;

    public VuePersonnage(Pane pane, Personnage personnage) {
        this.personnage = personnage;
        this.spritePersonnage = new ImageView();
        this.numeroImagePersonnage=1;
        spritePersonnage.setId(personnage.getId());
        spritePersonnage.setFitWidth(personnage.getLargeur());
        spritePersonnage.setFitHeight(personnage.getLongueur());
        spritePersonnage.setTranslateX(personnage.getPositionX());
        spritePersonnage.setTranslateY(personnage.getPositionY());

    }


    protected abstract void creerOmbre(Pane pane);

    public abstract void animation();

    public ImageView getSpritePersonnage() {
        return spritePersonnage;
    }

    public void setSpritePersonnage(Image spritePersonnage) {
        this.spritePersonnage.setImage(spritePersonnage);
    }

    public int getNumeroImagePersonnage() {
        return numeroImagePersonnage;
    }

    public void setNumeroImagePersonnage(int numeroSpritePersonnage) {
        this.numeroImagePersonnage = numeroSpritePersonnage;
    }

    public Personnage getPersonnage() {
        return personnage;
    }
}
