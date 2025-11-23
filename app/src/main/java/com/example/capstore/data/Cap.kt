package com.example.capstore.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Cap(
    val id: Int,
    @StringRes val nameRes: Int,
    val price: Double,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)