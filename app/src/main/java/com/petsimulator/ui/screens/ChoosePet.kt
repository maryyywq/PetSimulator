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
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.unit.dp
import com.petsimulator.R
import com.petsimulator.ui.GifAnimation

@Composable
fun ChoosePet(
    onPetSelected: (String, String) -> Unit
) {
    var petName by remember { mutableStateOf("") }
    var selectedPet by remember { mutableStateOf("") }

    val petOptions = listOf("Котик", "Собачка", "Хомяк")

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
                    else -> GifAnimation(R.drawable.choose_screen_question)
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(text = "Выберите питомца:", style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(16.dp))
            petOptions.forEach { pet ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (selectedPet == pet),
                            onClick = { selectedPet = pet }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedPet == pet),
                        onClick = { selectedPet = pet }
                    )
                    Text(text = pet)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = petName,
                onValueChange = { petName = it },
                label = { Text("Имя питомца") }
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
