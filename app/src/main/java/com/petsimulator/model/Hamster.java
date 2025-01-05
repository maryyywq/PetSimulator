package com.petsimulator.model;

public class Hamster extends Pet {
    public Hamster() { super(); setMoveBehavior(new RunBehavior());}

    public Hamster(String name, int age, Sex sex, Color color) {
        super(name, age, sex, color, null, new RunBehavior());
    }
}

