package com.petsimulator.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class OwnerWithPetAndItems(
    @Embedded val owner: OwnerEntity,
    @Relation(
        parentColumn = "pet_id",
        entityColumn = "id"
    )
    val pet: PetEntity?,
    @Relation(
        parentColumn = "id",
        entityColumn = "owner_id"
    )
    val items: List<ItemEntity>
)
