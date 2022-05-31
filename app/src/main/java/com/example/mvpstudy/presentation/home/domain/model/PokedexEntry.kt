package com.example.mvpstudy.presentation.home.domain.model

data class PokedexEntry(
    val pokemonName: String,
    var imageUrl: String? = null,
    val number: String
) {
    init {
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${this.number}.png"
    }
}
