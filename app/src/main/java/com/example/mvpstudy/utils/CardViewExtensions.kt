package com.example.mvpstudy.utils

import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils
import coil.load

fun CardView.createPokemonCard(imageUrl: String?, imageView: ImageView) {
    this.run {
        imageView.load(imageUrl) {
            this.target {
                calcDominantColor(it)
                    ?.let { dominantColor ->
                        setCardBackgroundColor(
                            ColorUtils.setAlphaComponent(
                                dominantColor,
                                99
                            )
                        )
                    }
            }
            imageView.load(imageUrl)
        }
    }
}