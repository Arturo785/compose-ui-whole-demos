package com.example.android.composeuidemos.instagram_ui

import androidx.compose.ui.graphics.painter.Painter

data class ImageWithText(
    val image: Painter,
    val text: String
)