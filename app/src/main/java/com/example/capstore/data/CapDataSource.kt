package com.example.capstore.data

import com.example.capstore.R

object CapDataSource {

    private val caps = listOf(
        Cap(
            id = 1,
            nameRes = R.string.cap_name_classic_navy,
            price = 24.99,
            descriptionRes = R.string.cap_description_classic_navy,
            imageRes = R.drawable.cap1
        ),
        Cap(
            id = 2,
            nameRes = R.string.cap_name_sport_green,
            price = 26.50,
            descriptionRes = R.string.cap_description_sport_green,
            imageRes = R.drawable.cap2
        ),
        Cap(
            id = 3,
            nameRes = R.string.cap_name_denim_blue,
            price = 28.00,
            descriptionRes = R.string.cap_description_denim_blue,
            imageRes = R.drawable.cap3
        ),
        Cap(
            id = 4,
            nameRes = R.string.cap_name_city_black,
            price = 31.25,
            descriptionRes = R.string.cap_description_city_black,
            imageRes = R.drawable.cap4
        )
    )

    fun getCapCollection(): CapCollection {
        return CapCollection(caps)
    }

    fun findCapById(capId: Int?): Cap? {
        return caps.find { it.id == capId }
    }
}

