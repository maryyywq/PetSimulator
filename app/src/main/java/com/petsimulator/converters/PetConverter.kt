package com.petsimulator.converters

import com.petsimulator.database.entities.PetEntity
import com.petsimulator.model.Cat
import com.petsimulator.model.Color
import com.petsimulator.model.Dog
import com.petsimulator.model.Hamster
import com.petsimulator.model.Pet
import com.petsimulator.model.Sex

fun PetEntity.toPet(): Pet {
    return when (type) {
        "Dog" -> Dog(
            name, age, Sex.valueOf(sex),
            Color.valueOf(color), satiety,
            energy, health
        )
        "Cat" -> Cat(
            name, age, Sex.valueOf(sex),
            Color.valueOf(color), satiety,
            energy, health
        )
        "Hamster" -> Hamster(
            name, age, Sex.valueOf(sex),
            Color.valueOf(color), satiety,
            energy, health
        )
        else -> throw IllegalArgumentException("Неизвестный питомец: $type")
    }
}

fun Pet.toEntity(): PetEntity {
    return PetEntity(
        name = name,
        age = age,
        satiety = satiety,
        energy = energy,
        health = health,
        sex = sex.name,
        color = color.name,
        type = this::class.simpleName ?: "Неизвестный" //Имя класса, например, Dog
    )
}
