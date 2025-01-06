package com.petsimulator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.petsimulator.R

@Composable
fun AskUserName(onNameEntered: (String) -> Unit) {
    var userName by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val imageRequest = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.welcome_cat)
                .decoderFactory { result, options, _ ->
                    ImageDecoderDecoder(result.source, options)
                }
                .crossfade(true)
                .build()

            AsyncImage(
                model = imageRequest,
                contentDescription = "Загрузка GIF",
                modifier = Modifier.size(250.dp)
            )
            Text(text = "Введите ваше имя:", style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Ваше имя") }

            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { if (userName.isNotEmpty()) onNameEntered(userName) }
            ) {
                Text("Далее")
            }
        }
    }
}
