package com.petsimulator.utils

import com.petsimulator.R
import com.petsimulator.model.Cat
import com.petsimulator.model.Color
import com.petsimulator.model.Dog
import com.petsimulator.model.Hamster
import com.petsimulator.model.Pet
import com.petsimulator.model.PetItem

fun imageChooser(item: PetItem) : Int {
    return when (item.name) {
        "Нурофен" -> R.drawable.nurofen
        "Йодомарин" -> R.drawable.iodomarin
        "Витамин D" -> R.drawable.vit_d
        "Косточка" -> R.drawable.bone
        "Вискас" -> R.drawable.whiskas
        "Зёрнышки" -> R.drawable.seeds
        "Травка" -> R.drawable.grass
        "Педигри" -> R.drawable.pedigree
        "Арбуз" -> R.drawable.watermelon
        else -> R.drawable.question_mark
    }
}

fun imageChooser(pet: Pet) : Int {
    return when {
        pet is Cat -> when(pet.color) {
            Color.BLACK -> R.drawable.black_cat
            Color.RED -> R.drawable.red_cat
            Color.WHITE -> R.drawable.white_cat
            Color.MULTI -> R.drawable.unspecified_cat
            else -> R.drawable.question_mark
        }
        pet is Dog -> when(pet.color) {
            Color.BLACK -> R.drawable.black_dog
            Color.RED -> R.drawable.red_dog
            Color.WHITE -> R.drawable.white_dog
            Color.MULTI -> R.drawable.unspecified_dog
            else -> R.drawable.question_mark
        }
        pet is Hamster -> when(pet.color) {
            Color.BLACK -> R.drawable.black_hamster
            Color.RED -> R.drawable.red_hamster
            Color.WHITE -> R.drawable.white_hamster
            Color.MULTI -> R.drawable.unspecified_hamster
            else -> R.drawable.question_mark
        }
        else -> R.drawable.question_mark
    }
}