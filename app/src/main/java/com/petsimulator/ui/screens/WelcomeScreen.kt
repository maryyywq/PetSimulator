package com.petsimulator.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petsimulator.R
import com.petsimulator.ui.GifAnimation
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreenWithAnimation(
    userName: String,
    petName: String,
    onAnimationEnded: () -> Unit
) {
    var isFirstTextVisible by remember { mutableStateOf(false) }
    var isSecondTextVisible by remember { mutableStateOf(false) }

    // Анимация текста
    LaunchedEffect(Unit) {
        delay(500)  //Небольшая задержка перед появлением первого текста
        isFirstTextVisible = true
        delay(4000)  //Пауза перед исчезновением первого текста
        isFirstTextVisible = false
        delay(1500)  //Небольшая пауза перед появлением второго текста
        isSecondTextVisible = true
        delay(4000)  //Пауза перед завершением анимации
        isSecondTextVisible = false
        delay(1000)  //Пауза перед завершением
        onAnimationEnded()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        //Первый текст
        AnimatedVisibility(
            visible = isFirstTextVisible,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Text(
                text = "Добро пожаловать, $userName!",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                lineHeight = 50.sp
            )
        }

        //Второй текст
        AnimatedVisibility(
            visible = isSecondTextVisible,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GifAnimation(R.drawable.welcome_cats, size = 300.dp)

                Text(
                    text = "Ваш $petName ждет вас!",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                    lineHeight = 50.sp
                )
            }
        }
    }
}
