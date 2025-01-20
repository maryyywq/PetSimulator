package com.petsimulator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.petsimulator.Constants.pinkColor
import com.petsimulator.R
import com.petsimulator.model.Cat
import com.petsimulator.model.Dog
import com.petsimulator.model.Hamster
import com.petsimulator.model.Pet
import com.petsimulator.model.Sex
import com.petsimulator.ui.theme.getAppTheme
import com.petsimulator.utils.GifAnimation
import com.petsimulator.utils.GradientText
import com.petsimulator.utils.playSound
import com.petsimulator.utils.stopSound
import kotlin.reflect.KClass
import com.petsimulator.model.Color as PetColor

@Composable
fun ChoosePet(
    onPetSelected: (String, KClass<Pet>, PetColor, Sex) -> Unit
) {
    var selectedPetName by remember { mutableStateOf("") }
    var selectedPetType by remember { mutableStateOf<KClass<out Pet>?>(null) }
    var selectedPetColor by remember { mutableStateOf<PetColor?>(null) }
    var selectedPetSex by remember { mutableStateOf<Sex?>(null) }

    val context = LocalContext.current

    val theme = getAppTheme()

    Box(
        modifier = Modifier.fillMaxSize().background(theme.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val petOptions = listOf(
                Triple("Котик", Cat::class, R.raw.cat_choose_sound),
                Triple("Собачка", Dog::class, R.raw.dog_choose_sound),
                Triple("Хомяк", Hamster::class, R.raw.hamster_choose_sound)
            )

            val pagerState = rememberPagerState() { petOptions.size }

            val currentPage by remember { derivedStateOf { pagerState.currentPage } }

            LaunchedEffect(currentPage) {
                playSound(context = context, soundResId = petOptions[currentPage].third, playOnce = false)
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally //Центровка по горизонтали
                ) {
                    //Текст над гифкой
                    Text(
                        text = "Ваш выбор: ${petOptions[page].first}",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(bottom = 16.dp),
                        color = theme.textColor
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    //Гифка
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f), //Растягиваем, чтобы текст и гифка не пересекались
                        contentAlignment = Alignment.Center
                    ) {
                        GifAnimation(
                            when (petOptions[page].second) {
                                Cat::class -> R.drawable.choose_screen_cat
                                Dog::class -> R.drawable.choose_screen_dog
                                Hamster::class -> R.drawable.choose_screen_hamster
                                else -> R.drawable.choose_screen_question
                            }
                        )
                    }

                    selectedPetType = petOptions[currentPage].second
                }
            }

            val sexOptions = listOf(
                Sex.MALE to Color.Blue,
                Sex.FEMALE to pinkColor
            )

            Spacer(modifier = Modifier.height(25.dp))
            Text(text = "Выберите пол питомца:", style = MaterialTheme.typography.titleLarge, color = theme.textColor)
            Spacer(modifier = Modifier.height(16.dp))

            sexOptions.forEach { (petSex, petColor) ->
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
                            selectedColor = petColor,
                            unselectedColor = Color.LightGray
                        )
                    )
                    Text(text = petSex.toString(), color = theme.textColor)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Выберите цвет питомца:", style = MaterialTheme.typography.titleLarge, color = theme.textColor)
            Spacer(modifier = Modifier.height(16.dp))

            val colorOptions = listOf(
                PetColor.RED to Color.Red,
                PetColor.BLACK to Color.Black,
                PetColor.WHITE to Color.White,
                PetColor.MULTI to Color.Magenta
            )

            colorOptions.forEach { (petColor, radioButtonTextColor) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (selectedPetColor == petColor),
                            onClick = {
                                selectedPetColor = petColor
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedPetColor == petColor),
                        onClick = {
                            selectedPetColor = petColor
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = radioButtonTextColor,
                            unselectedColor = Color.LightGray
                        )
                    )
                    if (radioButtonTextColor == Color.Unspecified && selectedPetColor == PetColor.MULTI)
                        GradientText(text = petColor.maleName)
                    else
                        Text(text = petColor.maleName, color = theme.textColor)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = selectedPetName,
                onValueChange = { selectedPetName = it },
                label = { Text("Имя питомца") },
                placeholder = { Text("Просто имя вашего любимца...") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = theme.textInputColor,
                    unfocusedContainerColor = theme.textInputColor,
                    disabledContainerColor = theme.textInputColor
                ),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = theme.buttonBackgroundColor),
                onClick = {
                    if (
                        selectedPetType != null &&
                        selectedPetName.isNotEmpty() &&
                        selectedPetSex != null &&
                        selectedPetColor != null
                        ) {
                            stopSound()
                            onPetSelected(
                                selectedPetName,
                                selectedPetType as KClass<Pet>,
                                selectedPetColor!!,
                                selectedPetSex!!
                            )
                        }
                }
            ) {
                Text(text = "Сохранить", color = theme.textColor)
            }
        }
    }
}
