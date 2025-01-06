package com.petsimulator.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.petsimulator.data.UserData
import com.petsimulator.ui.screens.AskUserName
import com.petsimulator.ui.screens.ChoosePet
import com.petsimulator.ui.screens.WelcomeScreen

@Composable
fun AppContent(
    userData: UserData?,
    onSaveUserData: (UserData) -> Unit
) {
    var currentStep by remember { mutableIntStateOf(0) }
    var userName by remember { mutableStateOf("") }

    when (currentStep) {
        0 -> AskUserName { enteredName ->
            userName = enteredName
            currentStep++
        }
        1 -> ChoosePet { petType, petName ->
            val newUserData = UserData(userName, petName, petType)
            onSaveUserData(newUserData)
            currentStep++
        }
        else -> WelcomeScreen(userData!!)
    }
}
