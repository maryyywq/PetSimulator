package com.petsimulator.converters

import com.petsimulator.database.entities.ItemEntity
import com.petsimulator.model.Cat
import com.petsimulator.model.Dog
import com.petsimulator.model.Food
import com.petsimulator.model.Hamster
import com.petsimulator.model.Medicine
import com.petsimulator.model.Pet
import com.petsimulator.model.PetItem

fun ItemEntity.toItem(): PetItem {
    val petType = when(pettype) {
        "Cat" -> Cat::class
        "Dog" -> Dog::class
        "Hamster" -> Hamster::class
        "Pet" -> Pet::class
        else -> throw IllegalArgumentException("Неизвестный питомец: $pettype\"")
    }

    return when (type) {
        "Food" -> Food(
            name, value,
            cost, petType.java
        )
        "Medicine" -> Medicine(
            name, value,
            cost, petType.java
        )
        else -> throw IllegalArgumentException("Неизвестный предмет: $type")
    }
}

fun List<ItemEntity?>.toItems() : List<PetItem> {
    val items : MutableList<PetItem> = arrayListOf()
    this.forEach { it?.toItem()?.let { it1 -> items.add(it1) } }
    return items.toList()
}

fun PetItem.toEntity(): ItemEntity {
    return ItemEntity(
        name = name,
        type = this::class.simpleName ?: "Неизвестный",
        pettype = petUser.simpleName ?: "Неизвестный",
        value = value,
        cost = cost,
        )
}