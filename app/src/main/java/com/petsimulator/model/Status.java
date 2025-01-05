package com.petsimulator.model;

public class Status implements Cloneable {
    private int satiety;
    private int energy;
    private int health;
    private Mood mood;


    public static final int maxEnergy = 100;
    public static final int maxHealth = 100;
    public static final int maxSatiety = 100;


    public Status() {
        setSatiety(maxSatiety);
        setEnergy(maxEnergy / 2);
        setHealth(maxHealth);
        setMood(Mood.HAPPY);
    }

    public int getSatiety() {
        return satiety;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHealth() {
        return health;
    }

    public Mood getMood() {
        return mood;
    }


    public void setSatiety(int satiety) {
        if (satiety < 0) {
            throw new IllegalArgumentException("Сытость не может быть отрицательной!");
        }
        this.satiety = Math.min(satiety, maxSatiety);
    }

    public void setEnergy(int energy) {
        if (energy < 0) {
            throw new IllegalArgumentException("Энергия не может быть отрицательной!");
        }
        this.energy = Math.min(energy, maxEnergy);
    }

    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException("Здоровье не может быть отрицательным!");
        }
        this.health = Math.min(health, maxHealth);
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    @Override
    public Status clone() throws CloneNotSupportedException
    {
        return (Status)super.clone();
    }
}
