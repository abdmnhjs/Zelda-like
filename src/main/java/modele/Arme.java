package modele;

public class Arme {

    private String id;
    private static int compteurArme;
    private int pointAttaque;

    public Arme(int pointAttaque) {
        this.pointAttaque=pointAttaque;
    }
}
