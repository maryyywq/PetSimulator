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
import com.petsimulator.ui.GifAnimation

@Composable
fun AskUserName(onNameEntered: (String) -> Unit) {
    var userName by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GifAnimation(R.drawable.welcome_cat)
            Text(text = "Введите ваше имя:", style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Ваше имя") },
                singleLine = true,
                placeholder = { Text("Просто ваше имя... и всё...") }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    if (userName.isNotEmpty()) onNameEntered(userName)
                }
            ) {
                Text("Далее")
            }
        }
    }
}
