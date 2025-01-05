package com.petsimulator.model;

import java.util.*;

public class Owner {
    private String ownerName = "Некто";
    private int ownerAge;
    private int money;
    private Map<String, Pet> pets = new HashMap<>();
    private List<PetItem> itemInventory = new ArrayList<>();

    public Owner() { }

    public Owner(String ownerName, int ownerAge, int money) {
        setOwnerName(ownerName);
        setOwnerAge(ownerAge);
        setMoney(money);
    }


    public String getOwnerName() {
        return ownerName;
    }

    public int getOwnerAge() {
        return ownerAge;
    }

    public int getMoney() {
        return money;
    }

    public Map<String, Pet> getPets() {
        return pets;
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

    public void setOwnerAge(int ownerAge) {
        if (ownerAge <= 0) {
            throw new IllegalArgumentException("Возраст владельца должен быть положительным числом.");
        }
        this.ownerAge = ownerAge;
    }

    public void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Количество денег не может быть отрицательным.");
        }
        this.money = money;
    }

    public void addPet(Pet pet) {
        pets.put(pet.getName(), pet);
    }

    public void removePet(String name) {
        pets.remove(name);
    }

    public Pet getPet(String name) {
        Pet pet = pets.get(name);
        if (pet == null) {
            System.out.println("Такого питомца у этого хозяина нет!");
        }
        return pet;
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
            System.out.println("Такого предмета не существует!");
        }
    }

    public PetItem getItem(String name) {
        return itemInventory.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Такого предмета не существует!");
                    return null;
                });
    }

    public void sortItemsByValue() {
        itemInventory.sort((left, right) -> Integer.compare(right.getValue(), left.getValue()));
    }

    public void sortItemsByCost() {
        itemInventory.sort((left, right) -> Integer.compare(right.getCost(), left.getCost()));
    }

    public void pet(Pet pet)
    {
        Random r = new Random();
        int randNum = r.nextInt(100) + 1;
        if (randNum <= 60)
        {
            pet.setMood(Mood.HAPPY);
            System.out.println(pet.getName() + " радуется от того, что вы его (её) погладили!");
        }
        else
        {
            pet.setMood(Mood.ANGRY);
            System.out.println(pet.getName() + " злится! Он (она) не в настроении!");
        }
    }

    public void play(Pet pet, Game game)
    {
        if (pet.getEnergy() >= game.getEnergyCost()) {
            pet.setMood(Mood.HAPPY);
            pet.setEnergy(pet.getEnergy() - game.getEnergyCost());
            pet.setSatiety(pet.getSatiety() - Pet.satietyCost);
            System.out.println(game.getDescription());
            System.out.println(pet.getName() + " поиграл(а) в " + game.getGameName() + " и очень счастлив(а)!");
        } else {
            System.out.println(pet.getName() + " слишком устал(а) для игры.");
        }
    }

    @Override
    public String toString() {
        String result = "Имя владельца: " + getOwnerName() + "\n";
        result += "Возраст: " + getOwnerAge() + "\n";
        result += "Количество денег: " + getMoney() + "\n";
        result += "Питомцы: \n";
        for (Map.Entry<String, Pet> entry : getPets().entrySet()) {
            result += "\t" + entry.getKey() + "\n";
        }
        result += "Предметы в инвентаре: \n";
        for (PetItem item : getInventory()) {
            result += "\t" + item.getName() + "\n";
        }
        return result;
    }

}

