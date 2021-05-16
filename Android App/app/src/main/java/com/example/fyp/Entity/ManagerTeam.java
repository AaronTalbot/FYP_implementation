package com.example.fyp.Entity;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

public class ManagerTeam implements Serializable {
    private static final String TAG = "MANAGER TEAM ACTIVITY";


    private ArrayList<Integer> Teams;
    private ArrayList<Player> Manager_Team, Players;
    private ArrayList<Float> Prices;
    private float itb;



    public ManagerTeam() {
        itb = 0.0f;
        Manager_Team = new ArrayList<Player>();
        Teams = new ArrayList<Integer>();
        Prices = new ArrayList<Float>();
        for(int i = 0; i < 20; i++){
            Teams.add(0);
            if(i<15){
                Player T = new Player();
                Manager_Team.add(T);
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


    public void addPlayer(Player P,float price, int index){
        Log.d(TAG, "WE ARE IN ADDPLAYER FUNCTION");
        Log.d(TAG, "Player = " +P.getName());
        int t = P.getTeam()-1;
        Teams.set(t,Teams.get(t) + 1);
        Manager_Team.set(index, P);
        Prices.set(index,price);

    }


    public void populatelist(){
        //TODO
    }

    public String Defenders(){
        ArrayList<Player> Defenders = new ArrayList<Player>();
        for(int i = 0; i < Players.size();i++){
            if(Players.get(i).getPosition() == 2){
                Defenders.add(Players.get(i));
            }
        }
        float Differential = 0.0f;
        ArrayList<Player> different = new ArrayList<Player>();
        for(int i = 0; i < Manager_Team.size(); i++){
            float budget = Manager_Team.get(i).getCost() + itb;
            if(Manager_Team.get(i).getPosition() == 2){
                for(int j = 0; j < Defenders.size();j++){

                    if(budget >= Defenders.get(j).getCost() & !Manager_Team.contains(Defenders.get(j))){
                        if(Defenders.get(j).getPredicted_points() - Manager_Team.get(i).getPredicted_points() > Differential){
                            different.clear();
                            Differential = Defenders.get(j).getPredicted_points() - Manager_Team.get(i).getPredicted_points();
                            different.add(Defenders.get(j));
                            different.add(Manager_Team.get(i));
                            Log.d(TAG, "We are have found a defender");

                            Log.d(TAG, different.get(0).getName());
                            Log.d(TAG, different.get(1).getName());

                        }
                    }
                }
            }
        }
        Double down = Math.floor(Differential);
        String S = "";
        if(Differential == 0.0f){
             S = "According to the algorithm you have the optimal defenders for this game week.";

        }
        else{
             S = different.get(0).getName() + " has a greater potential than " + different.get(1).getName() + " by " + String.valueOf(Differential) + " points";
        }
        Log.d(TAG, S);
        return S;
    }

    public String Midfeilders(){
        ArrayList<Player> Midfeilders = new ArrayList<Player>();
        for(int i = 0; i < Players.size();i++){
            if(Players.get(i).getPosition() == 3){
                Midfeilders.add(Players.get(i));
            }
        }
        float Differential = 0.0f;
        ArrayList<Player> different = new ArrayList<Player>();
        for(int i = 0; i < Manager_Team.size(); i++){
            float budget = Manager_Team.get(i).getCost() + itb;
            if(Manager_Team.get(i).getPosition() == 3){
                for(int j = 0; j < Midfeilders.size();j++){
                    if(budget >= Midfeilders.get(j).getCost() & !Manager_Team.contains(Midfeilders.get(j))){
                        if(Midfeilders.get(j).getPredicted_points() - Manager_Team.get(i).getPredicted_points() > Differential){
                            different.clear();
                            Differential = Midfeilders.get(j).getPredicted_points() - Manager_Team.get(i).getPredicted_points();
                            different.add(Midfeilders.get(j));
                            different.add(Manager_Team.get(i));
                            Log.d(TAG, "We are have found a midfeidler");

                            Log.d(TAG, different.get(0).getName());
                            Log.d(TAG, different.get(1).getName());

                        }
                    }
                }
            }
        }
        Double down = Math.floor(Differential);
        String S = different.get(0).getName() + " has a greater potential than " + different.get(1).getName() + " by " + String.valueOf(Differential) + " points";
        Log.d(TAG, S);
        return S;
    }

    public String Attackers(){
        ArrayList<Player> Attackers = new ArrayList<Player>();
        for(int i = 0; i < Players.size();i++){
            if(Players.get(i).getPosition() == 4){
                Attackers.add(Players.get(i));
            }
        }
        float Differential = 0.0f;
        ArrayList<Player> different = new ArrayList<Player>();
        for(int i = 0; i < Manager_Team.size(); i++){
            float budget = Manager_Team.get(i).getCost() + itb;
            if(Manager_Team.get(i).getPosition() == 4){
                for(int j = 0; j < Attackers.size();j++){
                    if(budget >= Attackers.get(j).getCost() & !Manager_Team.contains(Attackers.get(j))){
                        if(Attackers.get(j).getPredicted_points() - Manager_Team.get(i).getPredicted_points() > Differential){
                            different.clear();
                            Differential = Attackers.get(j).getPredicted_points() - Manager_Team.get(i).getPredicted_points();
                            different.add(Attackers.get(j));
                            different.add(Manager_Team.get(i));
                            Log.d(TAG, "We are have found a attacker");


                            Log.d(TAG, different.get(0).getName());
                            Log.d(TAG, different.get(1).getName());
                        }
                    }
                }
            }
        }
        Double down = Math.floor(Differential);
        String S = different.get(0).getName() + " has a greater potential than " + different.get(1).getName() + " by " + String.valueOf(Differential) + " points";
        Log.d(TAG, S);
        return S;
    }

    public void addAll(ArrayList<Player> players) {
        Players = players;
    }
}
