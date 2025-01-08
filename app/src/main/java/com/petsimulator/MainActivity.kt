package com.petsimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.petsimulator.ui.AppContent
import com.petsimulator.ui.theme.PetSimulatorTheme
import com.petsimulator.viewmodel.OwnerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PetSimulatorTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val appViewModel: OwnerViewModel = viewModel()
    AppContent(viewModel = appViewModel)
}