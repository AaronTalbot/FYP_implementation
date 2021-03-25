package com.example.fyp.Entity;

import java.io.Serializable;

public class Player implements Serializable {

    private int Chance_Of_Playing,Position,Team,Code;
    private float Cost,Predicted_points;
    private String Name;

    public Player(int chance_Of_Playing, int position, int team, int code, float cost, float predicted_points, String name) {
        Chance_Of_Playing = chance_Of_Playing;
        Position = position;
        Team = team;
        Code = code;
        Cost = cost;
        Predicted_points = predicted_points;
        Name = name;
    }

    public Player() {
    }

    public int getChance_Of_Playing() {
        return Chance_Of_Playing;
    }

    public void setChance_Of_Playing(int chance_Of_Playing) {
        Chance_Of_Playing = chance_Of_Playing;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }

    public int getTeam() {
        return Team;
    }

    public void setTeam(int team) {
        Team = team;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        this.Code = code;
    }

    public float getCost() {
        return Cost;
    }

    public void setCost(float cost) {
        Cost = cost;
    }

    public float getPredicted_points() {
        return Predicted_points;
    }

    public void setPredicted_points(float predicted_points) {
        Predicted_points = predicted_points;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



}
