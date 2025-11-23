package com.example.capstore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.capstore.R
import com.example.capstore.data.Cap

@Composable
fun CapItemCard(
    cap: Cap,
    onAddToCart: (Cap) -> Unit,
    onClick: (Cap) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(cap) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = cap.imageRes),
                contentDescription = stringResource(id = cap.nameRes),
                modifier = Modifier.height(120.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = cap.nameRes))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(id = R.string.price_format, cap.price))
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { onAddToCart(cap) }) {
                Text(text = stringResource(id = R.string.action_add_to_cart))
            }
        }
    }
}

