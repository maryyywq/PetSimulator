package com.petsimulator.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.petsimulator.Constants
import com.petsimulator.ui.screens.AskUserName
import com.petsimulator.ui.screens.ChoosePet
import com.petsimulator.ui.screens.MainScreen
import com.petsimulator.ui.screens.WelcomeScreenWithAnimation
import com.petsimulator.utils.stopSound
import com.petsimulator.viewmodel.OwnerViewModel

@Composable
fun AppContent(ownerViewModel: OwnerViewModel) {
    val step : Int = if (ownerViewModel.owner.value == null) {
        0
    }
    else if (ownerViewModel.pet.value == null) {
        1
    }
    else {
        2
    }
    var currentStep by remember { mutableIntStateOf(step) }

    when (currentStep) {
        0 -> AskUserName { enteredName ->
            ownerViewModel.setOwnerName(enteredName)
            ownerViewModel.addMoney(Constants.startMoneyBonus)
            currentStep++
        }
        1 -> ChoosePet { petType, petName,  ->
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
