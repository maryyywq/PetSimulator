package com.petsimulator.model;

import androidx.annotation.NonNull;

public abstract class Pet implements Cloneable {
    protected String name = "";
    protected int age;
    protected Status status = new Status();
    protected Sex sex ;
    protected Color color;

    public static final int EnergyCost = 5;
    public static final int healthCost = 5;
    public static final int satietyCost = 10;
    public static final int sleepHungerCost = 40;

    public Pet() { }

    public Pet(String name, int age, Sex sex, Color color) {
        setName(name);
        setAge(age);
        setSex(sex);
        setColor(color);
    }

    public abstract String getSound();


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSatiety() {
        return status.getSatiety();
    }

    public int getEnergy() {
        return status.getEnergy();
    }

    public int getHealth() {
        return status.getHealth();
    }

    public Mood getMood() {
        return status.getMood();
    }

    public Sex getSex() { return sex; }

    public Color getColor() { return color; }


    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым.");
        }
        this.name = name;
    }

    public void setSatiety(int satiety) {
        if (satiety < 0 || satiety > Status.maxSatiety) {
            throw new IllegalArgumentException("Сытость должна быть от 0 до " + Status.maxSatiety);
        }
        status.setSatiety(satiety);
    }

    public void setEnergy(int energy) {
        if (energy < 0 || energy > Status.maxEnergy) {
            throw new IllegalArgumentException("Энергия должна быть от 0 до " + Status.maxEnergy);
        }
        status.setEnergy(energy);
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным.");
        }
        this.age = age;
    }

    public void setHealth(int health) {
        if (health < 0 || health > Status.maxHealth) {
            throw new IllegalArgumentException("Здоровье должно быть от 0 до " + Status.maxHealth);
        }
        status.setHealth(health);
    }

    public void setMood(Mood mood) {
        if (mood == null) {
            throw new IllegalArgumentException("Настроение не может быть пустым.");
        }
        status.setMood(mood);
    }

    public void setSex(Sex sex) {
        if (sex == null) {
            throw new IllegalArgumentException("Пол не может быть пустым.");
        }
        this.sex = sex;
    }

    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Окраска не может быть пустой.");
        }
        this.color = color;
    }

    public void use(PetItem item) {
        if (item.getPetUser().equals(this.getClass()) || item.getPetUser().equals(Pet.class))
        {
            String className = item.getClass().getSimpleName();
            switch (className)
            {
                case "Food":
                    int newSatiety = status.getSatiety() + item.getValue();
                    status.setSatiety(newSatiety);
                    if (status.getSatiety() == Status.maxSatiety) {
                        status.setMood(Mood.HAPPY);
                    }
                    System.out.println(name + " покушал(а) " + item.getName() + " и его(ее) голод уменьшился.");
                    break;
                case "Medicine":
                    status.setHealth(status.getHealth() + item.getValue());
                    if (status.getHealth() > Status.maxHealth) {
                        status.setHealth(Status.maxHealth);
                        status.setMood(Mood.HAPPY);
                    }
                    System.out.println(name + " принял(а) " + item.getName() + " и его(ее) здоровье улучшилось.");
                    break;
                default:
                    break;
            }
        }
        else
        {
            System.out.println(item.getName() + " не подходит для " + getName() + "!");
        }
    }

    public void walk(Weather weather) {
        if (weather == Weather.STORM || weather == Weather.RAINY || weather == Weather.WINDY) {
            status.setMood(Mood.AFRAID);
            status.setHealth(status.getHealth() - healthCost);
            if (status.getHealth() < 0) status.setHealth(0);
            System.out.println(name + " испугался(ась) из-за плохой погоды.");
        } else {
            status.setMood(Mood.HAPPY);
            System.out.println(name + " гуляет и наслаждается хорошей погодой.");
        }
        status.setEnergy(status.getEnergy() - EnergyCost);
        status.setSatiety(status.getSatiety() - satietyCost);
        if (status.getEnergy() < 0) status.setEnergy(0);
    }

    public void sleep(PetHouse house) {
        status.setEnergy(status.getEnergy() + house.getComfortLevel());
        status.setSatiety(status.getSatiety() - sleepHungerCost);
        if (status.getEnergy() > Status.maxEnergy) {
            status.setEnergy(Status.maxEnergy);
        }

        if (status.getEnergy() >= 50) {
            System.out.println(name + " хорошо отдохнул(а)!");
            status.setMood(Mood.HAPPY);
        } else {
            System.out.println(name + " не очень хорошо отдохнул(а) :(");
            status.setMood(Mood.SAD);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "Имя питомца: " + name + "\n" +
                "Возраст питомца: " + age + "\n" +
                "Пол: " + sex + "\n" +
                "Окраска: " + (sex.equals(Sex.MALE) ? color.getMaleName() : color.getFemaleName()) + "\n" +
                "Сытость: " + status.getSatiety() + "\n" +
                "Энергия: " + status.getEnergy() + "\n" +
                "Здоровье: " + status.getHealth() + "\n" +
                "Настроение: " + status.getMood();
    }

    @NonNull
    @Override
    public Pet clone() throws CloneNotSupportedException
    {
        Pet newPet = (Pet)super.clone();
        newPet.status = status.clone();
        return newPet;
    }
}

