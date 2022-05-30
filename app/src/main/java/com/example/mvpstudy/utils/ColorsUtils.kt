package com.example.mvpstudy.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.palette.graphics.Palette


fun calcDominantColor(drawable: Drawable): Int? {
    val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

    return Palette.from(bitmap).generate().dominantSwatch?.rgb
}