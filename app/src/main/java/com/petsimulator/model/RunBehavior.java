package com.petsimulator.model;

public class RunBehavior implements MoveBehavior{
    @Override
    public void move() {
        System.out.println("Ваш питомец побежал(а)!");
    }
}
