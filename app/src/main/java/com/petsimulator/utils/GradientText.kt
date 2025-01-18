package com.petsimulator.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun GradientText(text: String) {
    //Список цветов радуги
    val colors = listOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.Cyan,
        Color.Blue,
        Color.Magenta
    )

    //Создаём анимацию с бесконечным переходом
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedOffset = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    //Генерация градиента с плавным смещением
    val gradientBrush = Brush.horizontalGradient(
        colors = colors,
        startX = 0f,
        endX = 1000f * animatedOffset.value //Анимируем градиент
    )

    BasicText(
        text = text,
        style = TextStyle(brush = gradientBrush)
    )
}
