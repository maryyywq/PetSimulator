package com.petsimulator.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_preferences")

object UserPreferencesKeys {
    val USER_NAME = stringPreferencesKey("user_name")
    val PET_NAME = stringPreferencesKey("pet_name")
    val PET_TYPE = stringPreferencesKey("pet_type")
}

fun Context.readUserData(): Flow<UserData?> {
    val userNameKey = stringPreferencesKey("user_name")
    val petNameKey = stringPreferencesKey("pet_name")
    val petTypeKey = stringPreferencesKey("pet_type")

    return dataStore.data.map { preferences ->
        val userName = preferences[userNameKey]
        val petName = preferences[petNameKey]
        val petType = preferences[petTypeKey]

        if (userName != null && petName != null && petType != null) {
            UserData(userName, petName, petType)
        } else {
            null
        }
    }
}

suspend fun Context.saveUserData(userData: UserData) {
    val userNameKey = stringPreferencesKey("user_name")
    val petNameKey = stringPreferencesKey("pet_name")
    val petTypeKey = stringPreferencesKey("pet_type")

    dataStore.edit { preferences ->
        preferences[userNameKey] = userData.userName
        preferences[petNameKey] = userData.petName
        preferences[petTypeKey] = userData.petType
    }
}

fun Context.getUserData(context: Context): Flow<UserData?> {
    return context.dataStore.data.map { preferences ->
        val userName = preferences[UserPreferencesKeys.USER_NAME]
        val petName = preferences[UserPreferencesKeys.PET_NAME]
        val petType = preferences[UserPreferencesKeys.PET_TYPE]

        if (userName != null && petName != null && petType != null) {
            UserData(userName, petName, petType)
        } else {
            null
        }
    }
}
