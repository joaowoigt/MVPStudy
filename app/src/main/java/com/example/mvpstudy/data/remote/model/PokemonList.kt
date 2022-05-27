package com.example.mvpstudy.data.remote.model

import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry
import com.example.mvpstudy.presentation.home.domain.model.PokedexListEntry

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
) {
    fun mapToPokedexListEntry() = PokedexListEntry(
        pokedex = results.mapIndexed { index, result ->
            val number =  result.url.dropLast(1).takeLastWhile { it.isDigit() }
            val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
            PokedexEntry(
                pokemonName = result.name,
                imageUrl = imageUrl,
                number = number.toInt()
            )
        }
    )



}