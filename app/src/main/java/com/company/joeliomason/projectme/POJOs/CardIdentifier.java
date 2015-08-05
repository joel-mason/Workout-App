package com.company.joeliomason.projectme.POJOs;

import java.io.Serializable;

/**
 * Created by joelmason on 11/04/2015.
 */
public class CardIdentifier implements Serializable{

    int id;
    String name;

    public CardIdentifier(int id, String name) {

        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

