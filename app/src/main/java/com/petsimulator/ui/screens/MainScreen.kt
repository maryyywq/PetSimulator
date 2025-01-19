package com.petsimulator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.petsimulator.R
import com.petsimulator.model.Mood
import com.petsimulator.ui.theme.getAppTheme
import com.petsimulator.utils.imageChooser
import com.petsimulator.viewmodel.AppViewModel

@Composable
fun MainScreen(viewModel: AppViewModel, onContentSelected: (ChoiceSelection) -> Unit) {
    val owner = viewModel.owner.value
    val pet = viewModel.pet.value

    var showMenu by remember { mutableStateOf(false) }

    val theme = getAppTheme()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.backgroundColor),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        // Верхняя панель
        HealthStatusBar(
            health = pet?.health ?: 0,
            energy = pet?.energy ?: 0,
            satiety = pet?.satiety ?: 0,
            mood = pet?.mood ?: Mood.HAPPY,
            money = owner?.money ?: 0,
            topBarColor = theme.topBarColor,
            textColor = theme.textColor
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .pointerInput(Unit) {
                    detectTapGestures { showMenu = false }
                },
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(if(isSystemInDarkTheme()) R.drawable.night_background else R.drawable.day_background) // Замените на ваш ресурс
                    .decoderFactory { result, options, _ ->
                        ImageDecoderDecoder(result.source, options)
                    }
                    .build(),
                contentDescription = "Фон",
                contentScale = ContentScale.Crop, //Указывает, что изображение должно растягиваться и обрезаться для заполнения области
                modifier = Modifier.fillMaxSize() //Растягивает фон на весь экран
            )

            Image(
                painter = painterResource(id = imageChooser(pet!!)),
                contentDescription = "Питомец",
                modifier = Modifier
                    .size(400.dp)
                    .clickable { showMenu = true }
            )

            if (showMenu) {
                Box(
                    modifier = Modifier
                        .background(color = Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = theme.buttonBackgroundColor),
                            onClick = {
                                showMenu = false
                            }) {
                            Text("Поиграть с питомцем", color = theme.textColor)
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = theme.buttonBackgroundColor),
                            onClick = {
                                showMenu = false
                            }) {
                            Text("Погулять с питомцем", color = theme.textColor)
                        }
                    }
                }
            }
        }

        // Нижняя панель
        BottomNavigationBar(
            onShopClick = onContentSelected,
            onInventoryClick = onContentSelected,
            bottomBarColor = theme.bottomBarColor,
            buttonBackgroundColor = theme.buttonBackgroundColor,
            textColor = theme.textColor
        )
    }
}

@Composable
fun HealthStatusBar(health: Int, energy: Int, satiety: Int, mood: Mood, money: Int, topBarColor: Color, textColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(topBarColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Здоровье
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Здоровье", fontSize = 16.sp, color = textColor)
            LinearProgressIndicator(
                progress = { health / 100f },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(8.dp),
                color = Color(0xFFFF8686),
                trackColor = Color(0xFFE3F2FD),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Сытость", fontSize = 16.sp, color = textColor)
            LinearProgressIndicator(
                progress = { satiety / 100f },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(8.dp),
                color = Color(0xFF97F990),
                trackColor = Color(0xFFE3F2FD),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        //Энергия
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Энергия", fontSize = 16.sp, color = textColor)
            LinearProgressIndicator(
                progress = { energy / 100f },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(8.dp),
                color = Color(0xFFFF8500),
                trackColor = Color(0xFFC8E6C9),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Настроение
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Настроение", fontSize = 16.sp, color = textColor)
            Text(
                text = when (mood) {
                    Mood.HAPPY -> "😊"
                    Mood.ANGRY -> "😠"
                    Mood.AFRAID -> "😨"
                    else -> "😢"
                },
                fontSize = 24.sp,
                color = textColor
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Деньги
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.money_icon),
                contentDescription = "Деньги",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "$money ₽",
                fontSize = 18.sp,
                color = textColor
            )
        }
    }
}

@Composable
fun BottomNavigationBar(onShopClick: (ChoiceSelection) -> Unit, onInventoryClick: (ChoiceSelection) -> Unit, bottomBarColor: Color, buttonBackgroundColor: Color, textColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(bottomBarColor)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { onShopClick(ChoiceSelection.Shop) },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor)
        ) {
            Text("Магазин", fontSize = 18.sp, color = textColor)
        }

        Button(
            onClick = { onInventoryClick(ChoiceSelection.Inventory) },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor)
        ) {
            Text("Инвентарь", fontSize = 18.sp, color = textColor)
        }
    }
}