package com.petsimulator.utils

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

@Composable
fun GifAnimation(content: Any?, context: Context = LocalContext.current, description: String? = "Загрузка GIF", size: Dp = 250.dp) {
    val imageRequest = ImageRequest.Builder(context)
        .data(content)
        .decoderFactory { result, options, _ ->
            ImageDecoderDecoder(result.source, options)
        }
        .crossfade(true)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = description,
        modifier = Modifier.size(size)
    )
}