package com.petsimulator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.petsimulator.viewmodel.AppViewModel

@Composable
fun MainScreen(viewModel: AppViewModel, onNavigateToShop: () -> Unit, onNavigateToInventory: () -> Unit) {
    val owner = viewModel.owner.value
    val pet = viewModel.pet.value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        // Верхняя панель
        HealthStatusBar(
            health = pet?.health ?: 0,
            energy = pet?.energy ?: 0,
            mood = pet?.mood ?: Mood.HAPPY,
            money = owner?.money ?: 0
        )

        //Центр: изображение питомца
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            // Фон
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.background) // Замените на ваш ресурс
                    .decoderFactory { result, options, _ ->
                        ImageDecoderDecoder(result.source, options)
                    }
                    .build(),
                contentDescription = "Фон",
                contentScale = ContentScale.Crop, //Указывает, что изображение должно растягиваться и обрезаться для заполнения области
                modifier = Modifier.fillMaxSize() //Растягивает фон на весь экран
            )

            // Питомец
            var showMenu by remember { mutableStateOf(false) }

            Image(
                painter = painterResource(id = R.drawable.black_cat_laying),
                contentDescription = "Питомец",
                modifier = Modifier
                    .size(200.dp)
                    .clickable { showMenu = true }
            )

            if (showMenu) {
                Box(
                    modifier = Modifier
                        .background(Color.White, shape = CircleShape)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TextButton(onClick = {
                            //coroutineScope.launch { viewModel.playWithPet() }
                            showMenu = false
                        }) {
                            Text("Поиграть с питомцем")
                        }
                        TextButton(onClick = {
                            //coroutineScope.launch { viewModel.walkWithPet() }
                            showMenu = false
                        }) {
                            Text("Пойти гулять с питомцем")
                        }
                    }
                }
            }
        }

        // Нижняя панель
        BottomNavigationBar(
            onShopClick = onNavigateToShop,
            onInventoryClick = onNavigateToInventory
        )
    }
}

@Composable
fun HealthStatusBar(health: Int, energy: Int, mood: Mood, money: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Здоровье
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Здоровье", fontSize = 16.sp, color = Color.Black)
            LinearProgressIndicator(
                progress = { health / 100f },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(8.dp),
                color = Color(0xFF90CAF9),
                trackColor = Color(0xFFE3F2FD),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Энергия
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Энергия", fontSize = 16.sp, color = Color.Black)
            LinearProgressIndicator(
                progress = { energy / 100f },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(8.dp),
                color = Color(0xFF81C784),
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
            Text("Настроение", fontSize = 16.sp, color = Color.Black)
            Text(
                text = when (mood) {
                    Mood.HAPPY -> "😊"   //Радость
                    Mood.ANGRY -> "😠"   //Злость
                    Mood.AFRAID -> "😨"  //Испуг
                    else -> "😢"         //Грусть
                },
                fontSize = 24.sp
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
                tint = Color.Unspecified, // Чтобы использовать оригинальные цвета иконки
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "$money ₽",
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun BottomNavigationBar(onShopClick: () -> Unit, onInventoryClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onShopClick,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB2EBF2))
        ) {
            Text("Магазин", fontSize = 18.sp, color = Color.Black)
        }

        Button(
            onClick = onInventoryClick,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF9C4))
        ) {
            Text("Инвентарь", fontSize = 18.sp, color = Color.Black)
        }
    }
}

