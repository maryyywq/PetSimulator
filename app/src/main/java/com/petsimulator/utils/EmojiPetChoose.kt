package com.petsimulator.utils

import com.petsimulator.model.Cat
import com.petsimulator.model.Dog
import com.petsimulator.model.Hamster
import com.petsimulator.model.Pet

fun emojiPetChoose(petClass: Class<out Pet>): String {
    return when (petClass) {
        Cat::class.java -> "🐱"
        Dog::class.java -> "\uD83D\uDC36"
        Hamster::class.java -> "\uD83D\uDC39"
        else -> "\uD83C\uDF0E"
    }
}