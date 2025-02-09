package com.petsimulator.model;

import java.util.Random;

public class Cat extends Pet {

    public Cat() { super(); }

    public Cat(String name, int age, Sex sex, Color color, int satiety, int energy, int health) {
        super(name, age, sex, color, satiety, energy, health);
    }

    public void groom() {
        System.out.println("Ваш кот(кошка) " + name + " умылся(лась).");
        status.setMood(Mood.HAPPY);
        status.setEnergy(status.getEnergy() - EnergyCost);
    }

    @Override
    public String getSound()
    {
        return "Мяу";
    }
}

