package com.petsimulator

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
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
                val owner = LocalViewModelStoreOwner.current

                owner?.let {
                    val viewModel: OwnerViewModel = viewModel(
                        it,
                        "OwnerViewModel",
                        OwnerViewModelFactory(LocalContext.current.applicationContext as Application)
                    )
                    AppContent(viewModel)
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class OwnerViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OwnerViewModel(application) as T
    }
}