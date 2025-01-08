package com.petsimulator.model;

public class Dog extends Pet {
    public Dog() { super(); }

    public Dog(String name, int age, Sex sex, Color color, int satiety, int energy, int health) {
        super(name, age, sex, color, satiety, energy, health);
    }

    @Override
    public String getSound()
    {
        return "Гав";
    }
}

