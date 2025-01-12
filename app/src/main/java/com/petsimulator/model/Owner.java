package com.petsimulator.model;

import androidx.annotation.NonNull;

import java.util.*;

public class Owner {
    private String ownerName = "Некто";
    private int money;
    private Pet pet = null;
    private List<PetItem> itemInventory = new ArrayList<>();

    public Owner() { }

    public Owner(String ownerName, int money, Pet pet) {
        setOwnerName(ownerName);
        setMoney(money);
        setPet(pet);
    }


    public String getOwnerName() {
        return ownerName;
    }

    public int getMoney() {
        return money;
    }

    public List<PetItem> getInventory() {
        return itemInventory;
    }

    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя владельца не может быть пустым.");
        }
        this.ownerName = ownerName;
    }

    public void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Количество денег не может быть отрицательным.");
        }
        this.money = money;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void addItem(PetItem item) {
        itemInventory.add(item);
    }

    public void removeItem(String name) {
        PetItem itemToRemove = itemInventory.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (itemToRemove != null) {
            itemInventory.remove(itemToRemove);
        } else {
            throw new IllegalArgumentException("Такого предмета не существует!");
        }
    }

    public PetItem getItem(String name) {
        return itemInventory.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Такого предмета не существует!"));
    }

    public void sortItemsByValue() {
        itemInventory.sort((left, right) -> Integer.compare(right.getValue(), left.getValue()));
    }

    public void sortItemsByCost() {
        itemInventory.sort((left, right) -> Integer.compare(right.getCost(), left.getCost()));
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Имя владельца: " + getOwnerName() + "\n");
        result.append("Количество денег: ").append(getMoney()).append("\n");
        result.append("Питомец: \n");
        result.append(pet);
        result.append("Предметы в инвентаре: \n");
        for (PetItem item : getInventory()) {
            result.append("\t").append(item.getName()).append("\n");
        }
        return result.toString();
    }
}

