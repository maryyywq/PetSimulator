package com.petsimulator.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petsimulator.model.Color
import com.petsimulator.model.Pet
import com.petsimulator.model.Sex
import kotlinx.coroutines.delay
import kotlin.reflect.KClass

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Загрузка...",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            lineHeight = 50.sp
        )
    }
}

