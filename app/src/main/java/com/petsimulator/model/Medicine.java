package com.petsimulator.model;

public class Medicine extends PetItem {

    public Medicine() {
        super();
    }

    public Medicine(String name, int value, int cost, Class<? extends Pet> petUser) {
        super(name, value, cost);
        setPetUser(petUser);
    }
}

