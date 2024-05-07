package modele;


import entite.GamePanel;
import entite.Keyboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
        getPlayerImage();

    }

    public Link(Environnement environnement,GamePanel gamePanel,Keyboard keyH) {

        super(100, 0, environnement, 5);
        this.gamePanel=gamePanel;
        this.keyH =keyH;
        this.direction = "down";
        getPlayerImage();

    }

    public void getPlayerImage() {

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if (keyH.upPressed == true){
                direction = "up";
                 setPositionY(getPositionY()-getVitesseDéplacement());
            } else if (keyH.downPressed == true) {
                direction = "down";
                setPositionY(getPositionY()+getVitesseDéplacement());
            } else if (keyH.leftPressed == true) {
                direction = "left";
                setPositionX(getPositionX()-getVitesseDéplacement());
            } else if (keyH.rightPressed == true) {
                direction = "right";
                setPositionX(getPositionX()+getVitesseDéplacement());
            }

        }
    }

    public void draw(Graphics2D g2){

        g2.setColor(Color.white);
        g2.fillRect(getPositionX(), getPositionY(), gamePanel.getTileSize(),gamePanel.getTileSize());

    }


}
