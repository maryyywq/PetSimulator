package com.petsimulator.model;

import androidx.annotation.NonNull;

public enum Mood {
    HAPPY("Счастливый"),
    ANGRY("Злой"),
    SAD("Грустный"),
    AFRAID("Испуганный");

    private final String name;

    Mood(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}