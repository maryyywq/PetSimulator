package com.petsimulator.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.petsimulator.database.UserData
import com.petsimulator.stopSound
import com.petsimulator.ui.screens.AskUserName
import com.petsimulator.ui.screens.ChoosePet
import com.petsimulator.ui.screens.MainScreen
import com.petsimulator.ui.screens.WelcomeScreenWithAnimation

@Composable
fun AppContent(
    userData: UserData?,
    onSaveUserData: (UserData) -> Unit
) {
    var currentStep by remember { mutableIntStateOf(0) }
    var userName by remember { mutableStateOf("") }
    var savedUserData by remember { mutableStateOf<UserData?>(null) }

    when (currentStep) {
        0 -> AskUserName { enteredName ->
            userName = enteredName
            currentStep++
        }
        1 -> ChoosePet { petType, petName ->
            val newUserData = UserData(userName, petName, petType)
            stopSound()
            onSaveUserData(newUserData)
            currentStep++
        }
        2 -> WelcomeScreenWithAnimation(savedUserData?.userName ?: "Гость") {
            currentStep = 3
        }
        else -> MainScreen()
    }
}
