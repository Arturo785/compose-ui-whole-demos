package com.example.android.composeuidemos.meditation_ui

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class FeaturedItem(
    val title: String,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)
