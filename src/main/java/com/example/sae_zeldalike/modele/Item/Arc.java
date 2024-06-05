package com.example.sae_zeldalike.modele.Item;

import com.example.sae_zeldalike.Vue.VueFlèche;
import com.example.sae_zeldalike.modele.Environnement.Environnement;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Arc extends Arme{
    private ArrayList<Flèche> fleches;
    private Environnement environnement;
    public Arc(int dégâts, int rayonAttaque, Environnement environnement) {
        super(dégâts, rayonAttaque);
        this.fleches = new ArrayList<>();
        this.environnement = environnement;
    }

    public void tirerFleche(){
        this.environnement.getFlèchesEnDéplacement().add(this.fleches.get(0));
        this.fleches.remove(0);
    }

    public ArrayList<Flèche> getFleches() {
        return this.fleches;
    }
}
