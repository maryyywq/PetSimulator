package com.petsimulator.model;

public class Dog extends Pet {
    public Dog() { super(); }

    public Dog(String name, int age, Sex sex, Color color) {
        super(name, age, sex, color);
    }

    @Override
    public String getSound()
    {
        return "Гав";
    }
}

