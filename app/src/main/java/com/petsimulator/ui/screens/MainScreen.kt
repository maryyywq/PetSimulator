package com.petsimulator.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petsimulator.R
import com.petsimulator.ui.AnimatedCat

@Composable
fun MainScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val sprite = ImageBitmap.imageResource(R.drawable.black_cat_laying)
        Box(  // Центрируем анимацию
            modifier = Modifier.fillMaxSize(),  // Занимаем весь экран
            contentAlignment = Alignment.Center  // Центрируем содержимое
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally  // Центрируем по горизонтали
            ) {
                AnimatedCat(
                    sprite = sprite,
                    frameCount = 8,
                    targetWidth = 300,  // Ограничиваем размеры анимации
                    targetHeight = 300,
                    frameDuration = 200L
                )

                Text(text = "Продолжение следует... скоро... ", fontSize = 20.sp)
            }
        }
    }
}