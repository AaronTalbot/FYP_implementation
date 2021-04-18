package com.example.fyp.Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ManagerTeam implements Serializable {
    private ArrayList<Integer> Teams;
    private ArrayList<Player> Players;
    private ArrayList<Float> Prices;
    private float itb;



    public ManagerTeam() {
        itb = 0.0f;
        Players = new ArrayList<Player>();
        Teams = new ArrayList<Integer>();
        Prices = new ArrayList<Float>();
        for(int i = 0; i < 20; i++){
            Teams.add(0);
            if(i<15){
                Player T = new Player();
                Players.add(T);
                Prices.add(0.0f);
            }
        }
    }

    public float getItb() {
        return itb;
    }

    public void setItb(float itb) {
        this.itb = itb;
    }


    public boolean addPlayer(Player P,float price, int index){
        int t = P.getTeam()-1;
        Boolean b;
        if(Teams.get(t) > 3){
            Teams.set(t,Teams.get(t) + 1);
            Players.set(index, P);
            b = true;
        }
        else{
            b = false;
        }

        return b;
    }


    public void populatelist(){
        //TODO
    }
}
