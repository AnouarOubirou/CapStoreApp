package com.example.capstore.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.capstore.R
import com.example.capstore.data.Cap
import com.example.capstore.data.CapCollection
import com.example.capstore.data.toListFromIterator
import com.example.capstore.ui.components.CapItemCard

enum class HomeLayoutType {
    List, Grid
}

@Composable
fun HomeScreen(
    capCollection: CapCollection,
    layoutType: HomeLayoutType,
    onCapSelected: (Cap) -> Unit,
    onAddToCart: (Cap) -> Unit,
    modifier: Modifier = Modifier
) {
    // Using the iterator pattern instead of accessing the list directly.
    val caps = remember(capCollection) { capCollection.toListFromIterator() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = stringResource(id = R.string.home_section_description))
        when (layoutType) {
            HomeLayoutType.List -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(caps, key = { it.id }) { cap ->
                        CapItemCard(
                            cap = cap,
                            onAddToCart = onAddToCart,
                            onClick = onCapSelected,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }

            HomeLayoutType.Grid -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(160.dp),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(caps, key = { it.id }) { cap ->
                        CapItemCard(
                            cap = cap,
                            onAddToCart = onAddToCart,
                            onClick = onCapSelected
                        )
                    }
                }
            }
        }
    }
}

