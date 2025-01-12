package com.petsimulator.model;

import androidx.annotation.NonNull;

import java.util.Random;

public abstract class Pet implements Cloneable {
    protected String name = "";
    protected int age;
    protected Status status = new Status();
    protected Sex sex = Sex.getRandomSex();
    protected Color color = Color.getRandomColor();

    public static final int EnergyCost = 5;
    public static final int healthCost = 5;
    public static final int satietyCost = 10;
    public static final int sleepHungerCost = 40;

    public Pet() { }

    public Pet(String name, int age, Sex sex, Color color, int satiety, int energy, int health) {
        setName(name);
        setAge(age);
        setSex(sex);
        setColor(color);
        setEnergy(energy);
        setHealth(health);
        setSatiety(satiety);
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

    public String use(PetItem item) {
        if (item.getPetUser().equals(this.getClass()) || item.getPetUser().equals(Pet.class))
        {
            String className = item.getClass().getSimpleName();
            String sexPronoun = sex == Sex.MALE ? "его" : "её";
            String sexVerbEnding = sex == Sex.MALE ? "" : "а";
            switch (className)
            {
                case "Food":
                    int newSatiety = status.getSatiety() + item.getValue();
                    status.setSatiety(newSatiety);
                    if (status.getSatiety() == Status.maxSatiety) {
                        status.setMood(Mood.HAPPY);
                    }
                    return (name + " покушал" + sexVerbEnding + " " + item.getName() + " и " + sexPronoun + " голод уменьшился.");
                case "Medicine":
                    status.setHealth(status.getHealth() + item.getValue());
                    if (status.getHealth() > Status.maxHealth) {
                        status.setHealth(Status.maxHealth);
                        status.setMood(Mood.HAPPY);
                    }
                    return (name + " принял" + sexVerbEnding + " " + item.getName() + " и " + sexPronoun + " здоровье улучшилось.");
                default:
                    return null;
            }
        }
        else
        {
            throw new IllegalArgumentException(item.getName() + " не подходит для " + getName() + "!");
        }
    }

    public String walk(GameDay gameDay) {
        String res;
        if (gameDay.getWeather() == Weather.STORM || gameDay.getWeather() == Weather.RAINY || gameDay.getWeather() == Weather.WINDY) {
            status.setMood(Mood.AFRAID);
            status.setHealth(status.getHealth() - healthCost);
            if (status.getHealth() < 0) status.setHealth(0);
            String sexVerbEnding = sex == Sex.MALE ? "ся" : "ась";
            res = (name + " испугал" + sexVerbEnding + " из-за плохой погоды.");
        } else {
            status.setMood(Mood.HAPPY);
            res = (name + " гуляет и наслаждается хорошей погодой.");
        }
        status.setEnergy(status.getEnergy() - EnergyCost);
        status.setSatiety(status.getSatiety() - satietyCost);
        if (status.getEnergy() < 0) status.setEnergy(0);
        return res;
    }

    public String sleep(PetHouse house) {
        String res;

        status.setEnergy(status.getEnergy() + house.getComfortLevel());
        status.setSatiety(status.getSatiety() - sleepHungerCost);
        if (status.getEnergy() > Status.maxEnergy) {
            status.setEnergy(Status.maxEnergy);
        }

        String sexVerbEnding = getSex() == Sex.MALE ? "" : "а";

        if (status.getEnergy() >= 50) {
            res = (name + " хорошо отдохнул" + sexVerbEnding + "!");
            status.setMood(Mood.HAPPY);
        } else {
            res = (name + " не очень хорошо отдохнул" + sexVerbEnding + " :(");
            status.setMood(Mood.SAD);
        }

        return res;
    }

    public String pet()
    {
        Random r = new Random();
        int randNum = r.nextInt(100) + 1;
        if (randNum <= 60)
        {
            setMood(Mood.HAPPY);
            String sexPronoun = getSex() == Sex.MALE ? "его" : "её";
            return (getName() + " радуется от того, что вы " + sexPronoun + " погладили!");
        }
        else
        {
            setMood(Mood.ANGRY);
            String sexPronoun = getSex() == Sex.MALE ? "Он" : "Она";
            return (getName() + " злится! " + sexPronoun + " не в настроении!");
        }
    }

    public String play(Game game)
    {
        String sexVerbEnding = getSex() == Sex.MALE ? "" : "а";
        if (getEnergy() >= game.getEnergyCost()) {
            setMood(Mood.HAPPY);
            setEnergy(getEnergy() - game.getEnergyCost());
            setSatiety(getSatiety() - Pet.satietyCost);

            return (getName() + " поиграл" + sexVerbEnding + " в " + game.getGameName() + " и очень счастлив" + sexVerbEnding + "!");
        } else {
            return (getName() + " слишком устал" + sexVerbEnding + " для игры.");
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

