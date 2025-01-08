package com.petsimulator.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.petsimulator.database.entities.PetEntity

@Dao
interface PetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: PetEntity)

    @Query("SELECT * FROM pets WHERE id = 1")
    suspend fun getPet(): PetEntity?

    @Update
    suspend fun updatePet(pet: PetEntity)
}
