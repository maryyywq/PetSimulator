package com.petsimulator.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.petsimulator.Constants
import com.petsimulator.model.Owner
import com.petsimulator.model.Sex
import com.petsimulator.ui.screens.AskUserName
import com.petsimulator.ui.screens.ChoosePet
import com.petsimulator.ui.screens.LoadingScreen
import com.petsimulator.ui.screens.MainScreen
import com.petsimulator.ui.screens.WelcomeScreenWithAnimation
import com.petsimulator.utils.createPet
import com.petsimulator.utils.stopSound
import com.petsimulator.viewmodel.AppViewModel

@Composable
fun AppContent(appViewModel: AppViewModel) {
    //Проверяем текущее состояние данных
    val owner = appViewModel.owner.value
    val pet = appViewModel.pet.value

    val flag by appViewModel.isDataLoaded.observeAsState()

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
            appViewModel.setOwner(Owner())
            appViewModel.setOwnerName(enteredName)
            appViewModel.addMoney(Constants.startMoneyBonus)
            currentStep = 1
        }
        1 -> ChoosePet { petName, petType, petColor, petSex  ->
            appViewModel.setPet(createPet(petType))
            appViewModel.setPetName(petName)
            appViewModel.setPetColor(petColor)
            appViewModel.setPetSex(petSex)
            stopSound()
            currentStep = 2
        }
        2 -> WelcomeScreenWithAnimation(
            userName = appViewModel.owner.value?.ownerName ?: "Гость",
            petName = appViewModel.pet.value?.name ?: "Неизвестный",
            sex = appViewModel.pet.value?.sex ?: Sex.MALE
        ) {
            currentStep = 3
        }
        else -> MainScreen(
            viewModel = appViewModel,
            onNavigateToShop = { },
            onNavigateToInventory = { }
        )
    }
}
