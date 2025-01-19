package com.petsimulator

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.petsimulator.service.SleepWorker
import com.petsimulator.ui.AppContent
import com.petsimulator.ui.theme.PetSimulatorTheme
import com.petsimulator.viewmodel.AppViewModel
import java.time.Duration
import java.time.LocalTime
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        scheduleDailySleepTask()

        setContent {
            PetSimulatorTheme {
                val owner = LocalViewModelStoreOwner.current

                owner?.let {
                    val viewModel: AppViewModel = viewModel(
                        it,
                        "OwnerViewModel",
                        OwnerViewModelFactory(LocalContext.current.applicationContext as Application)
                    )
                    AppContent(viewModel)
                }
            }
        }
    }

    private fun scheduleDailySleepTask() {
        val currentTime = LocalTime.now()
        val sleepTime = LocalTime.of(6, 0)
        val initialDelay = if (currentTime.isBefore(sleepTime)) {
            Duration.between(currentTime, sleepTime).toMinutes()
        } else {
            Duration.between(currentTime, sleepTime.plusHours(24)).toMinutes()
        }

        val workRequest = PeriodicWorkRequestBuilder<SleepWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(initialDelay, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "DailySleepWorker",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }
}

class OwnerViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AppViewModel(application) as T
    }
}