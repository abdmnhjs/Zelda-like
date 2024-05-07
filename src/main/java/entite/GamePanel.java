package entite;

import modele.Environnement;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    private Environnement environnement;
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol =18;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;


    @Override
    public void run() {

    }
}
