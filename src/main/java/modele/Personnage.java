package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;
public abstract class Personnage {

    private String id;
    private static int compteurPersonnage=0;
    private int pointVie;
    private int pointAttaque;
    private IntegerProperty positionX;
    private IntegerProperty positionY;
    private int vitesseDeplacement;
    protected Environnement environnement;

    public Personnage( int pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY, int vitesseDéplacement) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = pointVie;
        this.pointAttaque = pointAttaque;
        this.positionX = new SimpleIntegerProperty(positionX);
        this.positionY = new SimpleIntegerProperty(positionY);
        this.vitesseDeplacement = vitesseDéplacement;
        this.environnement = environnement;
    }

    public Personnage( int pointVie, int pointAttaque, Environnement environnement, int vitesseDéplacement) {
        this.id = "P"+compteurPersonnage;
        compteurPersonnage++;
        this.pointVie = pointVie;
        this.pointAttaque = pointAttaque;
        this.environnement = environnement;
        Random random = new Random();
        this.positionX = new SimpleIntegerProperty(random.nextInt(environnement.getWidth() - 1));
        this.positionY = new SimpleIntegerProperty(random.nextInt(environnement.getHeight() - 1));
        this.vitesseDeplacement = vitesseDéplacement;

    }

    public void setPositionXProperty(int x){
        positionX.setValue(x);
    }
    public IntegerProperty getPositionXProperty() {
        return positionX;
    }
    public int getPositionX() {
        return positionX.getValue();
    }

    public int getPositionY() {
        return positionY.getValue();
    }
    public IntegerProperty getPositionYProperty() {
        return positionY;
    }
    public void setPositionYProperty(int y) {
        positionY.setValue(y);
    }

    public int getVitesseDeplacement() {
        return vitesseDeplacement;
    }

    public int getPointVie() {
        return pointVie;
    }
    public void setPointVie(int degats){
        this.pointVie -= degats;
    }

    public int getPointAttaque() {
        return pointAttaque;
    }
    public void faireDegats(Personnage personnage){
        if(this.pointAttaque <= personnage.getPointVie()){
            personnage.setPointVie(this.pointAttaque);
        }
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Personnage{" +
                "id='" + id + '\'' +
                ", pointVie=" + pointVie +
                ", pointAttaque=" + pointAttaque +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", vitesseDéplacement=" + vitesseDeplacement +
                ", environnement=" + environnement +
                '}';
    }
}
