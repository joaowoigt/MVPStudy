package com.example.mvpstudy.presentation.detail.domain.model


import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry

data class PokemonSpeciesEntry(
    val description: String,
    val evolutions: PokedexEntry?
)