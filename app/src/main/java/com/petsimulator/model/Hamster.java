package com.petsimulator.model;

public class Hamster extends Pet {
    public Hamster() { super();}

    public Hamster(String name, int age, Sex sex, Color color) {
        super(name, age, sex, color);
    }

    @Override
    public String getSound()
    {
        return "Пик-пик";
    }
}

