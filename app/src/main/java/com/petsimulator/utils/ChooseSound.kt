package com.petsimulator.utils

import com.petsimulator.R
import com.petsimulator.model.Cat
import com.petsimulator.model.Dog
import com.petsimulator.model.Hamster
import com.petsimulator.model.Pet

fun chooseSound(pet: Pet): Int? {
    return when (pet) {
        is Cat -> R.raw.cat_meow_sound
        is Dog -> R.raw.dog_choose_sound
        is Hamster -> R.raw.hamster_dance_sound
        else -> null
    }
}