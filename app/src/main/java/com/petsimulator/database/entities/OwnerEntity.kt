package com.petsimulator.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "owners",
)
data class OwnerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val name: String,
    val money: Int,
)
