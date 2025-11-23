package com.example.capstore.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capstore.R
import com.example.capstore.data.Cap

@Composable
fun CartScreen(
    cartItems: List<Cap>,
    modifier: Modifier = Modifier
) {
    val total = cartItems.sumOf { it.price }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {

        if (cartItems.isEmpty()) {
            Text(
                text = stringResource(id = R.string.cart_empty_message),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f, fill = true)
            ) {
                items(cartItems, key = { it.id }) { cap ->

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Image (optional if you have imageRes)
                                cap.imageRes?.let {
                                    Image(
                                        painter = painterResource(id = it),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(58.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                }

                                Column {
                                    Text(
                                        text = stringResource(id = cap.nameRes),
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 18.sp
                                    )
                                    Text(
                                        text = stringResource(id = R.string.price_format, cap.price),
                                        color = Color.Gray,
                                        textAlign = TextAlign.Start,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }

                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.cart_total_label),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = stringResource(id = R.string.price_format, total),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
            ) {
                Text("Checkout", fontSize = 18.sp)
            }
        }
    }
}


