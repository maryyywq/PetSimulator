package com.petsimulator.converters

import com.petsimulator.database.entities.ItemEntity
import com.petsimulator.model.Cat
import com.petsimulator.model.Dog
import com.petsimulator.model.Food
import com.petsimulator.model.Hamster
import com.petsimulator.model.Medicine
import com.petsimulator.model.PetItem

fun ItemEntity.toItem(): PetItem {
    val petType = when(pet_type) {
        "Cat" -> Cat::class
        "Dog" -> Dog::class
        "Hamster" -> Hamster::class
        else -> throw IllegalArgumentException("Неизвестный питомец: $pet_type\"")
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
        pet_type = petUser.simpleName ?: "Неизвестный",
        value = value,
        cost = cost,
        owner_id = 1
        )
}