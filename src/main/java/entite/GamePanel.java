package entite;

import modele.Environnement;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

    private Environnement environnement;
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol =18;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    final int FPS =60;

    Thread gameThread;
    Keyboard keyH = new Keyboard();


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.cyan);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);

    }

    public void startGameThread(){
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double intervale = 1000000000/FPS;
        double delta = 0;
        long tempsPrecedent = System.nanoTime();
        long tempsActuel;

        while (gameThread!=null){

            tempsActuel=System.nanoTime();
            delta+= (tempsActuel-tempsPrecedent)/intervale;
            tempsPrecedent=tempsActuel;
            if (delta>1){
                repaint();
                delta--;
            }
        }
    }



}


