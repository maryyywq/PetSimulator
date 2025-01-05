package com.petsimulator.model;

public class BarkSound implements SoundBehavior{
    @Override
    public void makeSound()  {
        System.out.println("Гав!");
    }
}