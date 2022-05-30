package com.example.mvpstudy.presentation.detail

object PokemonDetailContract {

    interface View {
        fun setupUI()
    }

    interface Presenter {
        fun retrievePokemonData()
    }
}