package com.example.capstore.navigation

import androidx.annotation.StringRes
import com.example.capstore.R


sealed class Screen(
    val route: String,
    @StringRes val titleRes: Int,
    val showTopBar: Boolean = true,
    val showBottomBar: Boolean = false
) {
    data object Welcome : Screen("welcome", R.string.screen_welcome_title, showTopBar = true)
    data object Login : Screen("login", R.string.screen_login_title)
    data object Signup : Screen("signup", R.string.screen_signup_title)
    data object Home : Screen("home", R.string.screen_home_title, showBottomBar = true)
    data object Products : Screen("products", R.string.screen_products_title, showBottomBar = true)
    data object Cart : Screen("cart", R.string.screen_cart_title, showBottomBar = true)
    data object Details : Screen("details/{capId}", R.string.screen_details_title, showBottomBar = true)

}