package com.petsimulator.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.petsimulator.Constants
import com.petsimulator.model.Owner
import com.petsimulator.ui.screens.AskUserName
import com.petsimulator.ui.screens.ChoosePet
import com.petsimulator.ui.screens.LoadingScreen
import com.petsimulator.ui.screens.MainScreen
import com.petsimulator.ui.screens.WelcomeScreenWithAnimation
import com.petsimulator.utils.createPet
import com.petsimulator.utils.stopSound
import com.petsimulator.viewmodel.OwnerViewModel

@Composable
fun AppContent(ownerViewModel: OwnerViewModel) {
    //Проверяем текущее состояние данных
    val owner = ownerViewModel.owner.value
    val pet = ownerViewModel.pet.value

    val flag by ownerViewModel.isDataLoaded.observeAsState()

    //Используем переменную состояния для шага
    var currentStep by remember {
        mutableIntStateOf(-1) //-1 означает, что идет инициализация
    }

    //Определяем шаг после инициализации данных
    if (currentStep == -1 && flag == true) {
        currentStep = when {
            owner == null -> 0
            pet == null -> 1
            else -> 2
        }
    }

    when (currentStep) {
        -1 -> LoadingScreen()
        0 -> AskUserName { enteredName ->
            ownerViewModel.setOwner(Owner())
            ownerViewModel.setOwnerName(enteredName)
            ownerViewModel.addMoney(Constants.startMoneyBonus)
            currentStep = 1
        }
        1 -> ChoosePet { petName, petType, petColor, petSex  ->
            ownerViewModel.setPet(createPet(petType))
            ownerViewModel.setPetName(petName)
            ownerViewModel.setPetColor(petColor)
            ownerViewModel.setPetSex(petSex)
            stopSound()
            currentStep = 2
        }
        2 -> WelcomeScreenWithAnimation(userName = ownerViewModel.owner.value?.ownerName ?: "Гость") {
            currentStep = 3
        }
        else -> MainScreen()
    }
}
