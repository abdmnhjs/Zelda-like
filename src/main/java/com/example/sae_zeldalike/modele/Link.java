package com.example.sae_zeldalike.modele;




import java.awt.image.BufferedImage;

public class Link extends Personnage {



    private String direction;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;


    public Link(Environnement environnement, int positionX, int positionY) {
        super(100, 0, environnement, positionX, positionY, 5);

        this.direction = "down";

    }

    public Link(Environnement environnement) {

        super(100, 0, environnement, 5);

        this.direction = "down";

    }





}
