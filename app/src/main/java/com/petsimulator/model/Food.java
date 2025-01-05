package com.petsimulator.model;

public class Food extends PetItem {

    public Food() {
        super();
    }

    public Food(String foodName, int nutritionValue, int cost, Class<? extends Pet> petUser) {
        super(foodName, nutritionValue, cost);
        setPetUser(petUser);
    }
}

