package com.petsimulator.model;

import androidx.annotation.NonNull;

import java.util.Random;

public enum Sex {
    FEMALE("Девочка"),
    MALE("Мальчик");
    private final String sexName;

    Sex(String name) {
        this.sexName = name;
    }

    @NonNull
    @Override
    public String toString() {
        return sexName;
    }

    public static Sex getRandomSex() {
        Random rand = new Random();
        var sexes = Sex.values();
        return sexes[rand.nextInt(sexes.length)];
    }
}
