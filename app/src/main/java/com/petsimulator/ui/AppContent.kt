package com.petsimulator.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.petsimulator.ui.screens.AskUserName
import com.petsimulator.ui.screens.ChoosePet
import com.petsimulator.ui.screens.MainScreen
import com.petsimulator.ui.screens.WelcomeScreenWithAnimation
import com.petsimulator.utils.stopSound
import com.petsimulator.viewmodel.OwnerViewModel

@Composable
fun AppContent(ownerViewModel: OwnerViewModel) {
    var currentStep by remember { mutableIntStateOf(0) }

    when (currentStep) {
        0 -> AskUserName { enteredName ->
            ownerViewModel.setOwnerName(enteredName)
            currentStep++
        }
        1 -> ChoosePet { petType, petName ->
            viewModel.
            stopSound()
            currentStep++
        }
        2 -> WelcomeScreenWithAnimation(savedUserData?.userName ?: "Гость") {
            currentStep = 3
        }
        else -> MainScreen()
    }
}
