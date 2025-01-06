package com.petsimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.lifecycle.lifecycleScope
import com.petsimulator.data.UserData
import com.petsimulator.data.getUserData
import com.petsimulator.data.readUserData
import com.petsimulator.data.saveUserData
import com.petsimulator.ui.AnimatedCat
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

                //MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val sprite = ImageBitmap.imageResource(R.drawable.black_cat_laying)
    AnimatedCat(
        sprite = sprite,
        frameCount = 8, //Количество кадров в спрайте
        targetWidth = 500, //Желаемая ширина кадра
        targetHeight = 500, //Желаемая высота кадра
        frameDuration = 200L
    )
}
