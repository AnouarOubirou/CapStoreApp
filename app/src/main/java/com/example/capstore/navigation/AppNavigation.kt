package com.example.capstore.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.capstore.data.Cap
import com.example.capstore.data.CapDataSource
import com.example.capstore.ui.components.AppBottomBar
import com.example.capstore.ui.components.AppTopBar
import com.example.capstore.ui.screens.CartScreen
import com.example.capstore.ui.screens.DetailsScreen
import com.example.capstore.ui.screens.HomeLayoutType
import com.example.capstore.ui.screens.HomeScreen
import com.example.capstore.ui.screens.LoginScreen
import com.example.capstore.ui.screens.SignupScreen
import com.example.capstore.ui.screens.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val cartItems = remember { mutableStateListOf<Cap>() }
    val capCollection = remember { CapDataSource.getCapCollection() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen: Screen = when (currentRoute?.substringBefore("/")) {
        Screen.Login.route -> Screen.Login
        Screen.Signup.route -> Screen.Signup
        Screen.Home.route -> Screen.Home
        Screen.Products.route -> Screen.Products
        Screen.Cart.route -> Screen.Cart
        "details" -> Screen.Details
        else -> Screen.Welcome
    }
    val canNavigateBack =
        navController.previousBackStackEntry != null &&
                currentScreen !is Screen.Home
    val navigateUp: () -> Unit = { navController.navigateUp() }

    Scaffold(
        topBar = {
            if (currentScreen.showTopBar) {
                AppTopBar(
                    titleRes = currentScreen.titleRes,
                    showBackButton = canNavigateBack,
                    onBackClick = navigateUp
                )
            }
        },
        bottomBar = {
            if (currentScreen.showBottomBar) {
                AppBottomBar(
                    currentScreen = currentScreen,
                    onNavigate = { selected ->
                        if (selected == currentScreen) return@AppBottomBar
                        navController.navigate(selected.route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Welcome.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Welcome.route) {
                WelcomeScreen(
                    onLoginClick = { navController.navigate(Screen.Login.route) }
                )
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    onLogin = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    },
                    onSignupClick = {
                        navController.navigate(Screen.Signup.route)
                    }
                )
            }
            composable(Screen.Signup.route) {
                SignupScreen(
                    onSignup = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    capCollection = capCollection,
                    layoutType = HomeLayoutType.Grid,
                    onCapSelected = { cap ->
                        navController.navigate("details/${cap.id}")
                    },
                    onAddToCart = { cap -> cartItems.add(cap)
                        navController.navigate(Screen.Cart.route)}
                )
            }
            composable(Screen.Products.route) {
                HomeScreen(
                    capCollection = capCollection,
                    layoutType = HomeLayoutType.List,
                    onCapSelected = { cap ->
                        navController.navigate("details/${cap.id}")
                    },
                    onAddToCart = { cap -> cartItems.add(cap) }
                )
            }
            composable("details/{id}") { backStackEntry ->
                val capId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                val selectedCap = CapDataSource.findCapById(capId)

                DetailsScreen(
                    selectedCap = selectedCap,
                    onAddToCart = { cap -> cartItems.add(cap)
                        navController.navigate(Screen.Cart.route)}
                )
            }
            composable(Screen.Cart.route) {
                CartScreen(cartItems = cartItems)
            }
        }
    }
}

