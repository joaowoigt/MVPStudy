package com.example.mvpstudy.presentation.home

import android.graphics.drawable.Drawable
import com.example.mvpstudy.presentation.home.domain.model.PokedexListEntry

object HomeContract {

    interface View {
        fun setupAdapter(pokedexListEntry: PokedexListEntry?)
        fun networkErrorToast()
        fun unknownErrorToast()
    }

    interface Presenter {
         fun retrieveData()
         fun calcDominantColor(drawable: Drawable): Int?
    }
}
