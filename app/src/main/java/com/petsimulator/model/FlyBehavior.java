package com.petsimulator.model;

public class FlyBehavior implements MoveBehavior{
    @Override
    public void move() {
        System.out.println("Ваш питомец полетел(а)!");
    }
}
