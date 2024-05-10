package modele;

public class Link extends Personnage {


    public Link(int pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY) {
        super(10, pointAttaque, environnement, positionX, positionY, 2);
    }

    public Link(int pointVie, int pointAttaque, Environnement environnement) {
        super(10, pointAttaque, environnement, 2);
    }


}
