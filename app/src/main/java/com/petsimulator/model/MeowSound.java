package com.petsimulator.model;

public class MeowSound implements SoundBehavior{
    @Override
    public void makeSound()  {
        System.out.println("Мяу!");
    }
}
