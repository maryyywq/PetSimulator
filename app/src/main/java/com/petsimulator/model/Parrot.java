package com.petsimulator.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class Parrot extends Pet {
    private Set<String> sounds = new HashSet<>();

    public Parrot() { super(); setSoundBehavior(new TweetSound()); setMoveBehavior(new FlyBehavior());}

    public Parrot(String name, int age, Sex sex, Color color) {
        super(name, age, sex, color, new TweetSound(), new FlyBehavior());
    }

    public void learnNewSound(String newSound) {
        Random random = new Random();
        if (random.nextBoolean()) {
            sounds.add(newSound);
            System.out.println(newSound);
        } else {
            System.out.println("Ваш попугай ничему не научился...");
        }
    }

    public void makeRandomSound() {
        if (sounds.isEmpty()) {
            System.out.println("Попугай молчит...");
            return;
        }

        int index = new Random().nextInt(sounds.size());
        var list = new ArrayList<>(sounds);
        System.out.println(list.get(index));
    }
}


