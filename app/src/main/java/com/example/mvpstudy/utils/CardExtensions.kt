package com.example.mvpstudy.utils

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils
import coil.load

fun ImageView.setDominantColorBackground(imageUrl: String?, cardView: CardView) {
    this.load(imageUrl) {
        this.target {
            calcDominantColor(it)
                ?.let { dominantColor -> cardView.setCardBackgroundColor(ColorUtils.setAlphaComponent(dominantColor, 99)) }
        }
    }
}