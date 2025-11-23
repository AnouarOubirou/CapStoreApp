package com.example.capstore.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.capstore.navigation.Screen

data class BottomNavItem(
    val screen: Screen,
    val icon: ImageVector,
    val labelRes: Int
)

@Composable
fun AppBottomBar(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    val items = listOf(
        BottomNavItem(Screen.Home, Icons.Default.Home, Screen.Home.titleRes),
        BottomNavItem(Screen.Products, Icons.Default.List, Screen.Products.titleRes),
        BottomNavItem(Screen.Cart, Icons.Default.ShoppingCart, Screen.Cart.titleRes)
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentScreen == item.screen,
                onClick = { onNavigate(item.screen) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(id = item.labelRes)
                    )
                },
                label = { Text(text = stringResource(id = item.labelRes)) }
            )
        }
    }
}

