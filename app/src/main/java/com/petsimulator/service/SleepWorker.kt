package com.petsimulator.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.petsimulator.converters.toEntity
import com.petsimulator.converters.toPet
import com.petsimulator.database.AppDatabase

class SleepWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val database = AppDatabase.getInstance(applicationContext)
            val pet = database.petDao().getPet()?.toPet() ?: throw Exception("Ошибка обновления состояния питомца")

            pet.sleep()
            database.petDao().updatePet(pet.toEntity())

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}