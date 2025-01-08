package com.petsimulator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.petsimulator.R
import com.petsimulator.utils.playSound
import com.petsimulator.ui.GifAnimation

@Composable
fun ChoosePet(
    onPetSelected: (String, String) -> Unit
) {
    var petName by remember { mutableStateOf("") }
    var selectedPet by remember { mutableStateOf("") }

    val context = LocalContext.current

    val petOptions = listOf(
        "Котик" to R.raw.cat_choose_sound,
        "Собачка" to R.raw.dog_choose_sound,
        "Хомяк" to R.raw.hamster_choose_sound
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.height(250.dp)) {
                when (selectedPet)  {
                    "Котик" -> GifAnimation(R.drawable.choose_screen_cat)
                    "Собачка" -> GifAnimation(R.drawable.choose_screen_dog)
                    "Хомяк" -> GifAnimation(R.drawable.choose_screen_hamster)
                    else -> GifAnimation(R.drawable.choose_screen_question, size = 200.dp)
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = "Выберите питомца:", style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(16.dp))

            petOptions.forEach { (pet, soundRes) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (selectedPet == pet),
                            onClick = {
                                selectedPet = pet
                                playSound(context, soundRes)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedPet == pet),
                        onClick = {
                            selectedPet = pet
                            playSound(context, soundRes)
                        }
                    )
                    Text(text = pet)
                }
            }


            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = petName,
                onValueChange = { petName = it },
                label = { Text("Имя питомца") },
                placeholder = { Text("Просто имя вашего любимца...") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { if (selectedPet.isNotEmpty() && petName.isNotEmpty()) onPetSelected(selectedPet, petName) }
            ) {
                Text("Сохранить")
            }
        }
    }
}
