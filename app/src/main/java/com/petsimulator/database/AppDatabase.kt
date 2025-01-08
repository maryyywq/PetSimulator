package com.petsimulator.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.petsimulator.database.dao.ItemDao
import com.petsimulator.database.dao.OwnerDao
import com.petsimulator.database.dao.PetDao
import com.petsimulator.database.entities.ItemEntity
import com.petsimulator.database.entities.OwnerEntity
import com.petsimulator.database.entities.PetEntity

@Database(entities = [OwnerEntity::class, PetEntity::class, ItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ownerDao(): OwnerDao
    abstract fun petDao(): PetDao
    abstract fun itemDao(): ItemDao
}
