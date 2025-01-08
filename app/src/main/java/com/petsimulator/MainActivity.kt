package com.petsimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.petsimulator.database.UserData
import com.petsimulator.database.readUserData
import com.petsimulator.database.saveUserData
import com.petsimulator.ui.AppContent
import com.petsimulator.ui.theme.PetSimulatorTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var userData by mutableStateOf<UserData?>(null)

        // Чтение данных
        lifecycleScope.launch {
            readUserData().collect { data ->
                userData = data
            }
        }
        setContent {
            PetSimulatorTheme {
                AppContent(
                    userData = userData,
                    onSaveUserData = { newUserData ->
                        lifecycleScope.launch {
                            saveUserData(newUserData)
                            userData = newUserData
                        }
                    }
                )
            }
        }
    }
}
