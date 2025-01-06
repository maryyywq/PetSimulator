package com.petsimulator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.petsimulator.data.UserData

@Composable
fun WelcomeScreen(userData: UserData) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Привет, ${userData.userName}! Ваш питомец ${userData.petName} (${userData.petType}) ждет вас!",
            style = MaterialTheme.typography.displayMedium
        )
    }
}
