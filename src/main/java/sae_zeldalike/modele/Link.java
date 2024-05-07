package sae_zeldalike.modele;

public class Link extends Personnage {


    public Link(float pointVie, int pointAttaque, Environnement environnement, int positionX, int positionY) {
        super(pointVie, pointAttaque, environnement, positionX, positionY, 2);
    }

    public Link(float pointVie, int pointAttaque, Environnement environnement) {
        super(pointVie, pointAttaque, environnement, 2);
    }

    @Override
    public void seDeplace() {

    }
}
