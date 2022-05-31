package com.example.mvpstudy.data.remote.model

import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry

data class EvolvesFromSpecies(
    val name: String,
    val url: String
) {
    fun mapToPokedexEntry() = PokedexEntry(
        pokemonName = this.name,
        number = url.dropLast(1).takeLastWhile { it.isDigit() },
    )
}