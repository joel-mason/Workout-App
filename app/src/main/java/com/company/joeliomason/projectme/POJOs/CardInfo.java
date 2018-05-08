package com.company.joeliomason.projectme.POJOs;

/**
 * Created by joelmason on 11/04/2015.
 */
public class CardInfo {

    public int id;
    public double weight;
    public int reps;

    public CardInfo(int id, double weight, int reps) {
        this.id = id;
        this.weight = weight;
        this.reps = reps;
    }

    public CardInfo() {}

    public int getName() {
        return id;
    }

    public void setName(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }


    public int getReps() {
        return reps;
    }


    public String toString()
    {
        return "id: " + id + ", weight: " + weight + ", reps: " + reps;
    }
}
