package com.petsimulator.converters

import com.petsimulator.database.entities.OwnerEntity
import com.petsimulator.database.entities.OwnerWithPetAndItems
import com.petsimulator.model.Owner

fun OwnerWithPetAndItems.toOwner(): Owner {
    val petModel = pet?.toPet()
    return Owner(owner.name, owner.age, owner.money, petModel)
}

fun Owner.toEntity(): OwnerEntity {
    return OwnerEntity(
        name = ownerName,
        age = ownerAge,
        money = money,
        pet_id = 1
    )
}

