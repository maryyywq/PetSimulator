package com.petsimulator.converters

import com.petsimulator.database.entities.OwnerEntity
import com.petsimulator.model.Owner
import com.petsimulator.model.Pet
import com.petsimulator.model.PetItem

fun OwnerEntity.toOwner(pet: Pet, items: List<PetItem>): Owner {
    val owner = Owner(name, money, pet)
    items.forEach {
        owner.addItem(it)
    }
    return owner
}

fun Owner.toEntity(): OwnerEntity {
    return OwnerEntity(
        name = ownerName,
        money = money,
        pet_id = 1
    )
}

