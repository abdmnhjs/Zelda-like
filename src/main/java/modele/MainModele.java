package modele;

public class MainModele {

    public static void main(String[] args) {
        Environnement e1 = new Environnement(5,5);
        e1.setTableau();
        for (int ligne=0;ligne< e1.getWidth();ligne++){
            for (int colonne=0;colonne<e1.getHeight();colonne++){
                while (!(ligne >=e1.getWidth()) || !(colonne >= e1.getHeight())){
                System.out.println(e1.getTableau()[ligne][colonne]);

            }
        }
    }
}}
