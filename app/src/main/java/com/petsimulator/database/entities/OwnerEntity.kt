package com.petsimulator.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "owners",
    foreignKeys = [ForeignKey(
        entity = PetEntity::class,
        parentColumns = ["id"],
        childColumns = ["pet_id"],
        onDelete = ForeignKey.SET_NULL
    )]
)
data class OwnerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val name: String,
    val age: Int,
    val money: Int,
    val pet_id: Int? //Внешний ключ для связи с питомцем
)
