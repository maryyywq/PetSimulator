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
import com.petsimulator.ui.theme.getAppTheme
import com.petsimulator.utils.GifAnimation
import com.petsimulator.utils.playSound
import com.petsimulator.utils.stopSound
import kotlinx.coroutines.delay

@Composable
fun PlayingScreen(
    description: String,
    onPlayingEnded: () -> Unit
) {
    var isTextVisible by remember { mutableStateOf(false) }

    val theme = getAppTheme()

    val context = LocalContext.current

    //Анимация текста
    LaunchedEffect(Unit) {
        delay(1000)  //Небольшая задержка перед появлением первого текста
        isTextVisible = true
        playSound(context = context, soundResId = R.raw.keyboard_piano, playOnce = false)
        delay(60000)
        stopSound()
        onPlayingEnded()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
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
                GifAnimation(R.drawable.playing, size = 350.dp)

                Text(
                    text = description ,
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
