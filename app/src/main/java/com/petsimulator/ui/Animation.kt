package com.petsimulator.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.petsimulator.Constants
import kotlinx.coroutines.delay

@Composable
fun AnimatedCat(
    sprite: ImageBitmap,
    frameCount: Int,
    frameWidth: Int = Constants.frameWidth,
    frameHeight: Int = Constants.frameHeight,
    frameDuration: Long = 100L,
    targetWidth: Int = 300, //Желаемая ширина изображения
    targetHeight: Int = 300 //Желаемая высота изображения
) {
    var currentFrameIndex by remember { mutableIntStateOf(0) }

    //Анимация переключения кадров
    LaunchedEffect(Unit) {
        while (true) {
            delay(frameDuration)
            currentFrameIndex = (currentFrameIndex + 1) % frameCount
        }
    }

    // Центрирование анимации
    Box(
        modifier = Modifier
            .fillMaxSize() // Занимаем всё пространство экрана
            .background(Color.White), // Белый фон (можно убрать или заменить)
        contentAlignment = Alignment.Center // Выравнивание содержимого по центру
    ) {
        // Отрисовка текущего кадра
        Canvas(
            modifier = Modifier.size(targetWidth.dp, targetHeight.dp) // Размер анимации
        ) {
            // Вычисляем координаты текущего кадра на спрайт-листе
            val frameX = (currentFrameIndex % (sprite.width / frameWidth)) * frameWidth
            val frameY = (currentFrameIndex / (sprite.width / frameWidth)) * frameHeight

            // Отображаем кадр, используя clipRect
            drawImage(
                image = sprite,
                srcOffset = IntOffset(frameX, frameY),
                srcSize = IntSize(frameWidth, frameHeight),
                dstSize = IntSize(size.width.toInt(), size.height.toInt()) // Растяжение кадра
            )
        }
    }
}

