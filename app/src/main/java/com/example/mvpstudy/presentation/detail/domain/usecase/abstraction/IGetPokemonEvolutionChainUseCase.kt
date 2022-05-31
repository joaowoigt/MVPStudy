package com.example.mvpstudy.presentation.detail.domain.usecase.abstraction

import com.example.mvpstudy.presentation.detail.domain.model.EvolutionChainEntry
import kotlinx.coroutines.flow.Flow

interface IGetPokemonEvolutionChainUseCase {
    suspend fun execute(pokemonID: String): Flow<EvolutionChainEntry>
}