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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.petsimulator.model.PetItem
import com.petsimulator.ui.theme.getAppTheme
import com.petsimulator.utils.GradientText
import com.petsimulator.utils.imageChooser
import com.petsimulator.viewmodel.AppViewModel

@Composable
fun ShopScreen(
    shopItems: List<PetItem>,
    viewModel: AppViewModel,
    onBackClick: () -> Unit
) {
    val theme = getAppTheme()
    val openDialog = remember { mutableStateOf(false) }

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
            Spacer(modifier = Modifier.height(30.dp))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                //Группируем предметы по парам (2 в строке)
                items(shopItems.chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween //Равномерное распределение
                    ) {
                        rowItems.forEach { item ->
                            Column(
                                modifier = Modifier
                                    .weight(1f) // Равный размер для всех элементов
                                    .padding(horizontal = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = imageChooser(item)
                                    ),
                                    contentDescription = item.name,
                                    modifier = Modifier.size(80.dp)
                                )
                                if (item.name == "Арбуз")
                                {
                                    GradientText(
                                        text = item.name,
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                                else {
                                    Text(
                                        text = item.name,
                                        fontSize = 20.sp,
                                        color = theme.textColor,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                                Text(
                                    text = "Цена: ${item.cost}",
                                    fontSize = 16.sp,
                                    color = theme.textColor,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                                Text(
                                    text = "Ценность: ${item.value}",
                                    fontSize = 16.sp,
                                    color = theme.textColor,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                                Button(
                                    onClick = {
                                        viewModel.buyItem(item)
                                        openDialog.value = true
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = theme.buttonBackgroundColor),
                                    modifier = Modifier.padding(top = 8.dp)
                                ) {
                                    Text("Купить", color = theme.textColor)
                                }
                            }
                        }

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
                onDismissRequest = { openDialog.value = false },
                text = {
                    Text(
                        text = viewModel.message.value ?: "Сообщение отсутствует",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = theme.textColor
                    )
                },
                confirmButton = {
                    Button(
                        onClick = { openDialog.value = false },
                        colors = ButtonDefaults.buttonColors(containerColor = theme.buttonBackgroundColor),
                        ) {
                        Text("OK", fontSize = 20.sp, color = theme.textColor)
                    }
                },
                containerColor = theme.backgroundColor,
                textContentColor = theme.textColor,
            )
        }
    }
}


