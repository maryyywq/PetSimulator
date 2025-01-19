package com.petsimulator.model;

import java.util.Random;

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

    public static Weather getRandomWeather() {
        Random rand = new Random();
        var weathers = Weather.values();
        return weathers[rand.nextInt(weathers.length)];
    }
}
