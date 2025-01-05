package com.petsimulator.model;

public class PetHouse {
    private String houseName;
    private String address;
    private int comfortLevel;
    private static int HouseCount = 0;


    public static final int maxComfort = 100;

    public PetHouse() {
        this.houseName = "";
        this.address = "";
        this.comfortLevel = 0;
    }


    public PetHouse(String houseName, String address, int comfortLevel) {
        setHouseName(houseName);
        setAddress(address);
        setComfortLevel(comfortLevel);
        HouseCount++;
    }

    public static int getHouseCount() {
        return HouseCount;
    }

    public String getHouseName() {
        return houseName;
    }

    public String getAddress() {
        return address;
    }

    public int getComfortLevel() {
        return comfortLevel;
    }


    public void setHouseName(String houseName) {
        if (houseName == null || houseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Название дома не может быть пустым.");
        }
        this.houseName = houseName;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Адрес дома не может быть пустым.");
        }
        this.address = address;
    }

    public void setComfortLevel(int comfortLevel) {
        if (comfortLevel < 0) {
            throw new IllegalArgumentException("Уровень комфорта не может быть отрицательным.");
        }
        this.comfortLevel = Math.min(comfortLevel, maxComfort);
    }
}
