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
import androidx.compose.ui.unit.dp

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
