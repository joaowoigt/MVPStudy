package com.example.mvpstudy.data.remote.model

import com.example.mvpstudy.presentation.detail.domain.model.EvolutionChainEntry
import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry

data class EvolutionChain(
    val baby_trigger_item: Any,
    val chain: Chain,
    val id: Int
) {
    fun mapToEvolutionChainEntry() = EvolutionChainEntry(
        evolutions = this.chain.evolves_to.map { evolvesTo ->
            val number = evolvesTo.species.url.dropLast(1).takeLastWhile { it.isDigit() }
            PokedexEntry(
                pokemonName = evolvesTo.species.name,
                number = number,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
            )
        }
    )
}