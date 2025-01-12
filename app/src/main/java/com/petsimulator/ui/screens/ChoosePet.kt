package com.petsimulator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.petsimulator.Constants.pinkColor
import com.petsimulator.R
import com.petsimulator.model.Cat
import com.petsimulator.model.Color as PetColor
import com.petsimulator.model.Dog
import com.petsimulator.model.Hamster
import com.petsimulator.model.Pet
import com.petsimulator.model.Sex
import com.petsimulator.utils.playSound
import com.petsimulator.ui.GifAnimation
import kotlin.reflect.KClass

@Composable
fun ChoosePet(
    onPetSelected: (String, KClass<Pet>, PetColor, Sex) -> Unit
) {
    var selectedPetName by remember { mutableStateOf("") }
    var selectedPetType by remember { mutableStateOf<KClass<out Pet>?>(null) }
    var selectedPetColor by remember { mutableStateOf<PetColor?>(null) }
    var selectedPetSex by remember { mutableStateOf<Sex?>(null) }

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.height(250.dp)) {
                when (selectedPetType)  {
                    Cat::class -> GifAnimation(R.drawable.choose_screen_cat)
                    Dog::class -> GifAnimation(R.drawable.choose_screen_dog)
                    Hamster::class -> GifAnimation(R.drawable.choose_screen_hamster)
                    else -> GifAnimation(R.drawable.choose_screen_question, size = 200.dp)
                }
            }

            val petOptions = listOf(
                Triple("Котик", Cat::class, R.raw.cat_choose_sound),
                Triple("Собачка", Dog::class, R.raw.dog_choose_sound),
                Triple("Хомяк", Hamster::class, R.raw.hamster_choose_sound)
            )

            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Выберите питомца:", style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(16.dp))

            petOptions.forEach { (petClassName, petClass, soundRes) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (selectedPetType == petClass),
                            onClick = {
                                selectedPetType = petClass as KClass<Pet>
                                playSound(context, soundRes)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedPetType == petClass),
                        onClick = {
                            selectedPetType = petClass as KClass<Pet>
                            playSound(context, soundRes)
                        }
                    )
                    Text(text = petClassName)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Выберите пол питомца:", style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(16.dp))

            val sexOptions = listOf(
                Triple("Мужской", Sex.MALE, Color.Blue to Color.LightGray),
                Triple("Женский", Sex.FEMALE, pinkColor to Color.LightGray)
            )

            sexOptions.forEach { (petSexName, petSex, petColor) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (selectedPetSex == petSex),
                            onClick = {
                                selectedPetSex = petSex
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedPetSex == petSex),
                        onClick = {
                            selectedPetSex = petSex
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = petColor.first,
                            unselectedColor = petColor.second
                        )
                    )
                    Text(text = petSexName)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            //Потом здесь написать логику
            selectedPetColor = PetColor.getRandomColor()

            TextField(
                value = selectedPetName,
                onValueChange = { selectedPetName = it },
                label = { Text("Имя питомца") },
                placeholder = { Text("Просто имя вашего любимца...") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (
                        selectedPetType != null &&
                        selectedPetName.isNotEmpty() &&
                        selectedPetSex != null &&
                        selectedPetColor != null
                        ) {
                            onPetSelected(
                                selectedPetName,
                                selectedPetType as KClass<Pet>,
                                selectedPetColor!!,
                                selectedPetSex!!
                            )
                        }
                }
            ) {
                Text("Сохранить")
            }
        }
    }
}
