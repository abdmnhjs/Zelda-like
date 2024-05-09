package modele;


import entite.GamePanel;
import entite.Keyboard;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Link extends Personnage {


    GamePanel gamePanel;
    Keyboard keyH;
    private String direction;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;


    public Link(Environnement environnement, int positionX, int positionY,GamePanel gamePanel,Keyboard keyH) {
        super(100, 0, environnement, positionX, positionY, 5);
        this.gamePanel=gamePanel;
        this.keyH =keyH;
        this.direction = "down";

    }

    public Link(Environnement environnement,GamePanel gamePanel,Keyboard keyH) {

        super(100, 0, environnement, 5);
        this.gamePanel=gamePanel;
        this.keyH =keyH;
        this.direction = "down";

    }



    public void update(){
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if (keyH.upPressed == true){
                direction = "up";
                 setPositionYProperty(getPositionY()- getVitesseDeplacement());
            } else if (keyH.downPressed == true) {
                direction = "down";
                setPositionYProperty(getPositionY()+ getVitesseDeplacement());
            } else if (keyH.leftPressed == true) {
                direction = "left";
                setPositionXProperty(getPositionX()- getVitesseDeplacement());
            } else if (keyH.rightPressed == true) {
                direction = "right";
                setPositionXProperty(getPositionX()+ getVitesseDeplacement());
            }

        }
    }

    public void draw(Graphics2D g2){

        g2.setColor(Color.white);
        g2.fillRect(getPositionX(), getPositionY(), gamePanel.getTileSize(),gamePanel.getTileSize());

    }


}
