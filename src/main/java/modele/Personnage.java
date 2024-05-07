package modele;

import java.util.Random;
public abstract class Personnage {

    private String id;
    private static int compteurPersonnage=0;
    private int pointVie;
    private int pointAttaque;
    private int positionX;
    private int positionY;
    private int vitesseDéplacement;
    protected Environnement environnement;

    public Personnage( int pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY, int vitesseDéplacement) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = pointVie;
        this.pointAttaque = pointAttaque;
        this.positionX = positionX;
        this.positionY = positionY;
        this.vitesseDéplacement = vitesseDéplacement;
        this.environnement = environnement;
    }

    public Personnage( int pointVie, int pointAttaque, Environnement environnement, int vitesseDéplacement) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = pointVie;
        this.pointAttaque = pointAttaque;
        this.environnement = environnement;
        Random random = new Random();
        this.positionX = random.nextInt(environnement.getWidth() - 1);
        this.positionY = random.nextInt(environnement.getHeight() - 1);
        this.vitesseDéplacement = vitesseDéplacement;

    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getVitesseDéplacement() {
        return vitesseDéplacement;
    }

    public int getPointVie() {
        return pointVie;
    }
    public void setPointVie(int dégâts){
        this.pointVie -= dégâts;
    }

    public int getPointAttaque() {
        return pointAttaque;
    }
    public void faireDégâts(Personnage personnage){
        if(this.pointAttaque <= personnage.getPointVie()){
            personnage.setPointVie(this.pointAttaque);
        }
    }

    public String getId() {
        return id;
    }

    public abstract void seDeplace();

    @Override
    public String toString() {
        return "Personnage{" +
                "id='" + id + '\'' +
                ", pointVie=" + pointVie +
                ", pointAttaque=" + pointAttaque +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", vitesseDéplacement=" + vitesseDéplacement +
                ", environnement=" + environnement +
                '}';
    }
}
