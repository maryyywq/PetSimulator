package com.petsimulator.model;

public class TweetSound implements SoundBehavior{
    @Override
    public void makeSound()  {
        System.out.println("Чик-чирик!");
    }
}
