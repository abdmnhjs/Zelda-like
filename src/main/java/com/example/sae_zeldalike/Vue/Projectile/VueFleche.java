package com.example.sae_zeldalike.Vue.Projectile;

import com.example.sae_zeldalike.modele.Projectile.Projectile;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VueFleche extends VueProjectile{

    private Image flecheG1,flecheG2,flecheG3,flecheG4
            ,flecheH1,flecheH2,flecheH3,flecheH4,
            flecheD1,flecheD2,flecheD3,flecheD4,
            flecheB1,flecheB2,flecheB3,flecheB4;

    private int numeroImageProjectile;
    public VueFleche(Pane pane, Projectile projectile){
        super(pane,projectile);
        initialiserProjectile();
        spriteProjectile.setImage(null);
        this.numeroImageProjectile=1;

        pane.getChildren().add(spriteProjectile);
    }

    @Override
    public void animationProjectile() {
        switch (projectile.getDirection()){
            case "UP"->{
                if (getNumeroImageProjectile()==1){
                    spriteProjectile.setImage(flecheH1);
                    setNumeroImageProjectile(2);
                }else if (getNumeroImageProjectile()==2) {
                    spriteProjectile.setImage(flecheH2);
                    setNumeroImageProjectile(3);
                }else if (getNumeroImageProjectile()==3) {
                    spriteProjectile.setImage(flecheH3);
                    setNumeroImageProjectile(4);
                }else{
                    spriteProjectile.setImage(flecheH4);
                    setNumeroImageProjectile(1);
                }
            }
            case "DOWN"->{
                if (getNumeroImageProjectile()==1){
                    spriteProjectile.setImage(flecheB1);
                    setNumeroImageProjectile(2);
                }else if (getNumeroImageProjectile()==2) {
                    spriteProjectile.setImage(flecheB2);
                    setNumeroImageProjectile(3);
                }else if (getNumeroImageProjectile()==3) {
                    spriteProjectile.setImage(flecheB3);
                    setNumeroImageProjectile(4);
                }else{
                    spriteProjectile.setImage(flecheB4);
                    setNumeroImageProjectile(1);
                }
            }
            case "LEFT"->{
                if (getNumeroImageProjectile()==1){
                    spriteProjectile.setImage(flecheG1);
                    setNumeroImageProjectile(2);
                }else if (getNumeroImageProjectile()==2) {
                    spriteProjectile.setImage(flecheG2);
                    setNumeroImageProjectile(3);
                }else if (getNumeroImageProjectile()==3) {
                    spriteProjectile.setImage(flecheG3);
                    setNumeroImageProjectile(4);
                }else{
                    spriteProjectile.setImage(flecheG4);
                    setNumeroImageProjectile(1);
                }
            }case "RIGHT"->{
                if (getNumeroImageProjectile()==1){
                    spriteProjectile.setImage(flecheD1);
                    setNumeroImageProjectile(2);
                }else if (getNumeroImageProjectile()==2) {
                    spriteProjectile.setImage(flecheD2);
                    setNumeroImageProjectile(3);
                }else if (getNumeroImageProjectile()==3) {
                    spriteProjectile.setImage(flecheD3);
                    setNumeroImageProjectile(4);
                }else{
                    spriteProjectile.setImage(flecheD4);
                    setNumeroImageProjectile(1);
                }
            }
        }

    }

    @Override
    protected void initialiserProjectile() {

        flecheB1 = new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_DOWN_1.png");
        flecheB2 = new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_DOWN_2.png");
        flecheB3 = new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_DOWN_3.png");
        flecheB4=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_DOWN_4.png");
        flecheH1=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_UP_1.png");
        flecheH2=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_UP_2.png");
        flecheH3=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_UP_3.png");
        flecheH4=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_UP_4.png");
        flecheG1=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_LEFT_1.png");
        flecheG2=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_LEFT_2.png");
        flecheG3=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_LEFT_3.png");
        flecheG4=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_LEFT_4.png");
        flecheD1=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_RIGHT_1.png");
        flecheD2=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_RIGHT_2.png");
        flecheD3=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_RIGHT_3.png");
        flecheD4=new Image("file:src/main/resources/com/example/sae_zeldalike/Projectile/Fleche/fleche_RIGHT_4.png");


    }

    public int getNumeroImageProjectile() {
        return numeroImageProjectile;
    }
    public void setNumeroImageProjectile(int numeroImageProjectile) {
        this.numeroImageProjectile = numeroImageProjectile;
    }

    @Override
    public Image getImagePrincipale() {
        return flecheH1;
    }
}
