package com.petsimulator.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val name: String,
    val age: Int,
    val satiety: Int,
    val energy: Int,
    val health: Int,
    val sex: String,
    val color: String
)
