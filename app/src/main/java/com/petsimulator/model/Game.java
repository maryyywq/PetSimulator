package com.petsimulator.model;

public class Game {
    private String gameName = "";
    private String description = "Ничего не делали!";
    private int funValue = 10;
    private int energyCost = 5;


    public Game() { }

    public Game(String gameName, int funValue, int energyCost, String description) {
        setGameName(gameName);
        setFunValue(funValue);
        setEnergyCost(energyCost);
       setDescription(description);
    }


    public String getGameName() {
        return gameName;
    }

    public int getFunValue() {
        return funValue;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public String getDescription() { return description; }


    public void setGameName(String gameName) {
        if (gameName == null || gameName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя игры не может быть пустым!");
        }
        this.gameName = gameName;
    }

    public void setFunValue(int funValue) {
        if (funValue <= 0) {
            throw new IllegalArgumentException("Значение веселья должно быть положительным!");
        }
        this.funValue = funValue;
    }

    public void setEnergyCost(int energyCost) {
        if (energyCost < 0) {
            throw new IllegalArgumentException("Энергия не может быть отрицательной!");
        }
        this.energyCost = energyCost;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Описание игры не может быть пустым!");
        }
        this.description = description;
    }
}
