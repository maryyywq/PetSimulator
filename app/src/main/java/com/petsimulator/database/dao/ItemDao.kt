package com.petsimulator.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petsimulator.database.entities.ItemEntity

@Dao
interface ItemDao {
    @Insert
    suspend fun insertItem(item: ItemEntity)

    @Query("SELECT * FROM items")
    suspend fun getItems(): List<ItemEntity?>

    @Delete
    suspend fun deleteItem(item: ItemEntity)
}
