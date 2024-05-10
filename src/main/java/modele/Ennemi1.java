package modele;

public class Ennemi1 extends Personnage {

    public Ennemi1(int pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY) {
        super(100, pointAttaque, environnement, positionX, positionY, 2);
    }

    public Ennemi1(int pointVie, int pointAttaque, Environnement environnement) {
        super(100, pointAttaque, environnement, 2);
    }


}

