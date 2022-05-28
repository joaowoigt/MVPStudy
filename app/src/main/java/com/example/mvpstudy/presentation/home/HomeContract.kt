package com.example.mvpstudy.presentation.home

import android.graphics.drawable.Drawable
import com.example.mvpstudy.presentation.home.domain.model.PokedexListEntry
import kotlinx.coroutines.flow.MutableStateFlow

object HomeContract {

    interface View {
        fun observeFlow()
        fun networkErrorToast()
        fun unknownErrorToast()
    }

    interface Presenter {
         fun retrieveData()
         val pokedexFlow: MutableStateFlow<PokedexListEntry?>
    }
}
