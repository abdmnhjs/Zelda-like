package com.example.sae_zeldalike.modele.Personnage;

import com.example.sae_zeldalike.modele.Environnement.Environnement;

public class Ennemi1 extends Personnage {

    public Ennemi1(Environnement environnement, int positionX, int positionY) {
        super(100, 1, environnement, positionX, positionY, 5);
    }


    public void seDeplace(){
        int probaDeplacement = (int) (Math.random()*11);
        if (probaDeplacement<1){
            int probaX = (int) (Math.random()*5);
            int probaY = (int) (Math.random()*5);

            if (probaX<=3){
                int probaDirectionX = (int)(Math.random()*2);
                if(probaDirectionX==0){
                    setPositionXProperty(getPositionX()-getVitesseDeplacement());
                }
                if (probaDirectionX==1){
                    setPositionXProperty(getPositionX()+getVitesseDeplacement());
                }
            }
            if (probaY<=2){
                int probaDirectionY = (int)(Math.random()*2);
                if(probaDirectionY==0){
                    setPositionYProperty(getPositionY()-getVitesseDeplacement());
                }
                if (probaDirectionY==1){
                    setPositionYProperty(getPositionY()+getVitesseDeplacement());
                }
            }
        }
    }


}
