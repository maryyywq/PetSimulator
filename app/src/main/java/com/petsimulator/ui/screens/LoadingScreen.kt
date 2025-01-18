package com.petsimulator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petsimulator.R
import com.petsimulator.ui.theme.getAppTheme
import com.petsimulator.utils.GifAnimation

@Composable
fun LoadingScreen() {
    val theme = getAppTheme()

    Box(
        modifier = Modifier.fillMaxSize().background(theme.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GifAnimation(R.drawable.loading_screen)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Загрузка...",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                lineHeight = 50.sp,
                color = theme.textColor
            )
        }
    }
}

