package com.example.fyp.Entity;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GlobalVariable {
    private static final String TAG = "Global Variable Players";

    private static FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance("https://final-year-project-dd795-default-rtdb.europe-west1.firebasedatabase.app/");

    private static GlobalVariable Instance = null;

    private static final ArrayList<Player> Players = new ArrayList<Player>();

    public GlobalVariable() {
        DatabaseReference playerRef = mFirebaseDatabase.getReference("Players");
        DatabaseReference goalKeeperRef = playerRef.child("Goalkeepers");
        DatabaseReference defenderRef = playerRef.child("Defenders");
        DatabaseReference midfielderRef = playerRef.child("Midfeilders");
        DatabaseReference attackerRef = playerRef.child("Attackers");

        mFirebaseDatabase = FirebaseDatabase.getInstance("https://final-year-project-dd795-default-rtdb.europe-west1.firebasedatabase.app/");

        goalKeeperRef.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PopulatePlayers(snapshot);
                Log.i(TAG," : Database call GoalKeepers Successful");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG,error.toString());
            }
        });
        defenderRef.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PopulatePlayers(snapshot);
                Log.i(TAG," : Database call Defenders Successful");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG,error.toString());
            }
        });
        midfielderRef.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PopulatePlayers(snapshot);
                Log.i(TAG," : Database call Midfeilders Successful");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG,error.toString());
            }
        });
        attackerRef.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PopulatePlayers(snapshot);
                Log.i(TAG," : Database call Attackers Successful");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG,error.toString());
            }
        });

    }

    public static synchronized GlobalVariable getInstance() {
        if(Instance == null){
            Instance = new GlobalVariable();
        }
        return Instance;
    }

    private static synchronized void PopulatePlayers(DataSnapshot snapshot) {
        for(DataSnapshot ds : snapshot.getChildren()){

            Player P = new Player();
            int Code = ds.child("code").getValue(Integer.class);
            int COP = ds.child("Chance of playing next round").getValue(Integer.class);
            int Position = ds.child("Position").getValue(Integer.class);
            int Team = ds.child("Team").getValue(Integer.class);
            String Name = ds.child("Name").getValue(String.class);
            P.setCode(Code);
            P.setChance_Of_Playing(COP);
            P.setPosition(Position);
            P.setTeam(Team);
            P.setName(Name);
            if(P.getPosition() != 1){
                float pp = ds.child("Predicted Points").getValue(Float.class);
                float cost = ds.child("Cost").getValue(Float.class);
                P.setPredicted_points(pp);
                P.setCost(cost);
            }
            Players.add(P);
        }
    }

    public ArrayList<Player> getPlayers(){
        return Players;
    }
}
