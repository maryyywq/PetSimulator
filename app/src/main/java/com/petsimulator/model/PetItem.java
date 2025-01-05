package com.petsimulator.model;

public abstract class PetItem {
    protected String name = "";
    protected int value;
    protected int cost;
    protected Class<? extends Pet> petUser = Pet.class;

    public PetItem() { }

    public PetItem(String name, int value, int cost) {
        setName(name);
        setValue(value);
        setCost(cost);
    }


    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getCost() {
        return cost;
    }

    public Class<? extends Pet> getPetUser() {
        return petUser;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя предмета не может быть пустым.");
        }
        this.name = name;
    }

    public void setValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Значение предмета не может быть отрицательным.");
        }
        this.value = value;
    }

    public void setCost(int cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Стоимость предмета не может быть отрицательной.");
        }
        this.cost = cost;
    }

    public void setPetUser(Class<? extends Pet> petUser) {
        if (petUser == null) {
            throw new IllegalArgumentException("Тип питомца не может быть не определен.");
        }
        this.petUser = petUser;
    }
}
