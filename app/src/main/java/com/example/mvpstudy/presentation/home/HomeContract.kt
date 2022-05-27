package com.example.mvpstudy.presentation.home

import com.example.mvpstudy.presentation.home.domain.model.PokedexListEntry

object HomeContract {

    interface View {
        fun setupAdapter(pokedexListEntry: PokedexListEntry?)
    }

    interface Presenter {
         fun retrieveData()
    }
}
