package com.petsimulator.model;

public enum Weather {
    SUNNY("Солнечно"),
    RAINY("Дождливо"),
    WINDY("Ветрено"),
    STORM("Буря");
    private final String name;

    Weather(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
