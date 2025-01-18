package com.petsimulator.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class StandartShop implements Shop {
    @NonNull
    public static List<PetItem> getGoods() {
        var items = new ArrayList<PetItem>();
        items.add(new Medicine("Нурофен", 20, 100, Pet.class));
        items.add(new Medicine("Йодомарин", 15, 75, Pet.class));
        items.add(new Medicine("Витамин D", 5, 20, Cat.class));
        items.add(new Food("Косточка", 25, 50, Dog.class));
        items.add(new Food("Вискас", 25, 60, Cat.class));
        items.add(new Food("Зёрнышки", 20, 25, Hamster.class));
        items.add(new Food("Травка", 5, 10, Pet.class));
        return items;
    }
}
