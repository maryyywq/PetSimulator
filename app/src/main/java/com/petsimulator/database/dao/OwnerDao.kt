package com.petsimulator.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.petsimulator.database.entities.OwnerEntity

@Dao
interface OwnerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOwner(owner: OwnerEntity)

    @Query("SELECT * FROM owners WHERE id = 1")
    suspend fun getOwner(): OwnerEntity?

    @Update
    suspend fun updateOwner(owner: OwnerEntity)
}
