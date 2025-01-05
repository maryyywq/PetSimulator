package com.petsimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import com.petsimulator.ui.AnimatedCat
import com.petsimulator.ui.theme.PetSimulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetSimulatorTheme {
                MainScreen()
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
