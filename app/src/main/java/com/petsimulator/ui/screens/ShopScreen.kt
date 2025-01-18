package com.petsimulator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petsimulator.R
import com.petsimulator.model.PetItem

@Composable
fun ShopScreen(
    shopItems: List<PetItem>,
    onBuyClick: (PetItem) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = onBackClick) {
            Text("Назад")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(shopItems) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.item_image),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    Column {
                        Text(item.name, fontSize = 18.sp)
                        Text("Цена: ${item.cost}", fontSize = 16.sp)
                    }
                    Button(onClick = { onBuyClick(item) }) {
                        Text("Купить")
                    }
                }
            }
        }
    }
}
