package com.example.mvpstudy.presentation.detail.domain.usecase.abstraction

import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry
import kotlinx.coroutines.flow.Flow

interface IGetDescriptionAndEvolutionUseCase {
    fun execute(pokemonID: String): Flow<PokemonSpeciesEntry>
}