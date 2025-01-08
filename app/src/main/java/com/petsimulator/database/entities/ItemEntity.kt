package com.petsimulator.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "items",
    foreignKeys = [ForeignKey(
        entity = OwnerEntity::class,
        parentColumns = ["id"],
        childColumns = ["owner_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String,
    val pet_type: String,
    val value: Int,
    val cost: Int,
    val owner_id: Int //Внешний ключ для связи с владельцем
)
