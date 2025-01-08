package com.petsimulator.model;

public class Hamster extends Pet {
    public Hamster() { super();}

    public Hamster(String name, int age, Sex sex, Color color, int satiety, int energy, int health) {
        super(name, age, sex, color, satiety, energy, health);
    }

    @Override
    public String getSound()
    {
        return "Пик-пик";
    }
}

