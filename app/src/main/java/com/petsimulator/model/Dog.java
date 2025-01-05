package com.petsimulator.model;

public class Dog extends Pet {
    public Dog() { super(); setSoundBehavior(new BarkSound()); setMoveBehavior(new RunBehavior());}

    public Dog(String name, int age, Sex sex, Color color) {
        super(name, age, sex, color, new BarkSound(), new FlyBehavior());
    }

    @Override
    public void walk(Weather weather)
    {
        super.walk(weather);
        makeSound();
    }
}

