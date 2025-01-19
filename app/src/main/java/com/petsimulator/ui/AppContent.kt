package com.petsimulator.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.petsimulator.model.Mood
import com.petsimulator.model.Owner
import com.petsimulator.model.Sex
import com.petsimulator.model.StandartShop
import com.petsimulator.ui.screens.AskUserName
import com.petsimulator.ui.screens.ChoiceSelection
import com.petsimulator.ui.screens.ChoosePet
import com.petsimulator.ui.screens.InventoryScreen
import com.petsimulator.ui.screens.LoadingScreen
import com.petsimulator.ui.screens.MainScreen
import com.petsimulator.ui.screens.PlayingScreen
import com.petsimulator.ui.screens.ShopScreen
import com.petsimulator.ui.screens.SleepingScreen
import com.petsimulator.ui.screens.WalkingScreen
import com.petsimulator.ui.screens.WelcomeScreen
import com.petsimulator.utils.createPet
import com.petsimulator.utils.isPetSleeping
import com.petsimulator.viewmodel.AppViewModel

@Composable
fun AppContent(appViewModel: AppViewModel) {
    //Проверяем текущее состояние данных
    val owner = appViewModel.owner.value
    val pet = appViewModel.pet.value

    val flag by appViewModel.isDataLoaded.observeAsState()

    var currentScreen by remember {
        mutableStateOf(ChoiceSelection.Loading)
    }

    //Определяем шаг после инициализации данных
    if (currentScreen == ChoiceSelection.Loading && flag == true) {
        currentScreen = when {
            owner == null -> ChoiceSelection.AskUserName
            pet == null -> ChoiceSelection.ChoosePet
            else -> ChoiceSelection.Welcome
        }
    }

    when (currentScreen) {
        ChoiceSelection.Loading -> LoadingScreen()

        ChoiceSelection.AskUserName -> AskUserName { enteredName ->
            appViewModel.setOwner(Owner())
            appViewModel.setOwnerName(enteredName)
            currentScreen = ChoiceSelection.ChoosePet
        }

        ChoiceSelection.ChoosePet -> ChoosePet { petName, petType, petColor, petSex  ->
            appViewModel.setPet(createPet(petType))
            appViewModel.setPetName(petName)
            appViewModel.setPetColor(petColor)
            appViewModel.setPetSex(petSex)
            currentScreen = ChoiceSelection.Welcome
        }

        ChoiceSelection.Welcome -> WelcomeScreen(
            userName = appViewModel.owner.value?.ownerName ?: "Гость",
            petName = appViewModel.pet.value?.name ?: "Неизвестный",
            sex = appViewModel.pet.value?.sex ?: Sex.MALE
        ) {
            currentScreen = if (isPetSleeping())
                ChoiceSelection.Sleeping
            else
                ChoiceSelection.Main
        }

        ChoiceSelection.Main -> MainScreen(
            viewModel = appViewModel
        ) { choice ->
            currentScreen = choice
        }

        ChoiceSelection.Shop -> ShopScreen(
            viewModel = appViewModel,
            shopItems = StandartShop.getGoods(),
            onBackClick = {
                currentScreen = ChoiceSelection.Main
            }
        )

        ChoiceSelection.Inventory -> InventoryScreen(
            viewModel = appViewModel,
            onBackClick = {
                currentScreen = ChoiceSelection.Main
            }
        )

        ChoiceSelection.Sleeping -> SleepingScreen(
            petName = appViewModel.pet.value?.name ?: "Неизвестный",
            sex = appViewModel.pet.value?.sex ?: Sex.MALE
        ) {
            currentScreen = ChoiceSelection.Welcome
        }

        ChoiceSelection.Playing -> PlayingScreen(
            description = appViewModel.message.value!!
        ) {
            currentScreen = ChoiceSelection.Main
        }

        ChoiceSelection.Walking -> WalkingScreen(
            description = appViewModel.message.value!!,
            mood = appViewModel.pet.value?.mood ?: Mood.HAPPY
        ) {
            currentScreen = ChoiceSelection.Main
        }
    }
}
