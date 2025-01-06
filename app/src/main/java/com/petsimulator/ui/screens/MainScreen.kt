package com.petsimulator.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import com.petsimulator.R
import com.petsimulator.ui.AnimatedCat

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