package com.petsimulator.utils

import com.petsimulator.model.Pet
import kotlin.reflect.KClass

fun <T : Pet> createPet(petClass: KClass<T>): T {
    return petClass.java.getDeclaredConstructor().newInstance()
}