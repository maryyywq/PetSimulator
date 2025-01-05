package com.petsimulator.model;

public enum Sex {
    FEMALE("Девочка"),
    MALE("Мальчик");
    private final String name;

    Sex(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
