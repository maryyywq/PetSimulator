package com.petsimulator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petsimulator.R
import com.petsimulator.ui.theme.getAppTheme
import com.petsimulator.viewmodel.AppViewModel

@Composable
fun InventoryScreen(
    viewModel: AppViewModel,
    onBackClick: () -> Unit
) {
    val theme = getAppTheme()
    val openDialog = remember { mutableStateOf(false) }
    val dialogMessage = remember { mutableStateOf("") }

    val inventoryItems = viewModel.items.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f) //Занимает оставшееся пространство
            ) {
                //Группируем предметы по парам (2 в строке)
                items(inventoryItems!!.chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween //Равномерное распределение
                    ) {
                        // Обрабатываем каждый элемент в строке
                        rowItems.forEach { item ->
                            Column(
                                modifier = Modifier
                                    .weight(1f) //Равный размер для всех элементов
                                    .padding(horizontal = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = when (item.name) {
                                            "Нурофен" -> R.drawable.nurofen
                                            "Йодомарин" -> R.drawable.iodomarin
                                            "Витамин D" -> R.drawable.vit_d
                                            "Косточка" -> R.drawable.bone
                                            "Вискас" -> R.drawable.whiskas
                                            "Зёрнышки" -> R.drawable.seeds
                                            "Травка" -> R.drawable.grass
                                            "Педигри" -> R.drawable.pedigree
                                            "Арбуз" -> R.drawable.watermelon
                                            else -> R.drawable.question_mark
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier.size(64.dp)
                                )
                                Text(
                                    text = item.name,
                                    fontSize = 18.sp,
                                    color = theme.textColor,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Button(
                                        onClick = {
                                            viewModel.useItem(item)
                                            dialogMessage.value = viewModel.message.value!!
                                            openDialog.value = true
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = theme.buttonBackgroundColor),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text("\uD83D\uDC45", color = theme.textColor)
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Button(
                                        onClick = {
                                            viewModel.deleteItem(item)
                                            dialogMessage.value = "${item.name} выброшен!"
                                            openDialog.value = true
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = theme.buttonBackgroundColor),
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text("\uD83D\uDDD1\uFE0F", color = theme.textColor)
                                    }
                                }
                            }
                        }

                        // Если предметов в строке меньше двух, добавляем пустое пространство
                        if (rowItems.size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(containerColor = theme.buttonBackgroundColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Назад", color = theme.textColor)
            }
        }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                    onBackClick()
                                   },
                text = {
                    Text(
                        text = dialogMessage.value,
                        fontSize = 20.sp,
                        color = theme.textColor,
                        textAlign = TextAlign.Center,
                        lineHeight = 30.sp
                    )
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = theme.buttonBackgroundColor),
                        onClick = {
                        openDialog.value = false
                        onBackClick()
                    }) {
                        Text("OK", fontSize = 20.sp, color = theme.textColor)
                    }
                },
                containerColor = theme.backgroundColor,
                textContentColor = theme.textColor
            )
        }
    }
}
