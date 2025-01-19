package com.petsimulator.ui.screens

import com.petsimulator.utils.isPetSleeping
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petsimulator.R
import com.petsimulator.model.Sex
import com.petsimulator.ui.theme.getAppTheme
import com.petsimulator.utils.GifAnimation
import com.petsimulator.utils.playSound
import com.petsimulator.utils.stopSound
import kotlinx.coroutines.delay

@Composable
fun SleepingScreen(
    petName: String,
    sex: Sex,
    onSleepingEnded: () -> Unit
) {
    var isTextVisible by remember { mutableStateOf(false) }

    val theme = getAppTheme()

    val context = LocalContext.current

    //Анимация текста
    LaunchedEffect(Unit) {
        delay(1000)  //Небольшая задержка перед появлением первого текста
        isTextVisible = true
        playSound(context = context, soundResId = R.raw.sleeping_sound, playOnce = false)
        while (true) {
            if (!isPetSleeping()) {
                stopSound()
                onSleepingEnded()
            }
            delay(60000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        //Первый текст
        AnimatedVisibility(
            visible = isTextVisible,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GifAnimation(R.drawable.potato_sleeping, size = 350.dp)

                val sexVerbEnding = if (sex == Sex.MALE) "" else "а"

                Text(
                    text = "Ваш$sexVerbEnding $petName спит!",
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                    lineHeight = 50.sp,
                    color = theme.textColor
                )
            }
        }
    }
}
