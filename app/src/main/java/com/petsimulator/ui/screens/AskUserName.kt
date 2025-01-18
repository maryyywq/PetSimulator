package com.petsimulator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.petsimulator.R
import com.petsimulator.ui.theme.getAppTheme
import com.petsimulator.utils.GifAnimation

@Composable
fun AskUserName(onNameEntered: (String) -> Unit) {
    var userName by remember { mutableStateOf("") }

    val theme = getAppTheme()

    Box(
        modifier = Modifier.fillMaxSize().background(theme.backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GifAnimation(R.drawable.welcome_cat)
            Text(text = "Введите ваше имя:", style = MaterialTheme.typography.displaySmall, color = theme.textColor)
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Ваше имя") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = theme.textInputColor,
                    unfocusedContainerColor = theme.textInputColor,
                    disabledContainerColor = theme.textInputColor
                ),
                singleLine = true,
                placeholder = { Text("Просто ваше имя... и всё...")
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = theme.buttonBackgroundColor),
                onClick = {
                    if (userName.isNotEmpty()) onNameEntered(userName)
                }
            ) {
                Text(text = "Далее", color = theme.textColor)
            }
        }
    }
}
