package entite;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int touche =e.getKeyCode();

        if (touche == KeyEvent.VK_Z){
            upPressed = true;
        }if (touche == KeyEvent.VK_Q){
            leftPressed = true;
        }if (touche == KeyEvent.VK_S){
            downPressed = true;
        }if (touche == KeyEvent.VK_D){
            rightPressed = true;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

        int touche =e.getKeyCode();

        if (touche == KeyEvent.VK_Z){
            upPressed = false;
        }if (touche == KeyEvent.VK_Q){
            leftPressed = false;
        }if (touche == KeyEvent.VK_S){
            downPressed = false;
        }if (touche == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
