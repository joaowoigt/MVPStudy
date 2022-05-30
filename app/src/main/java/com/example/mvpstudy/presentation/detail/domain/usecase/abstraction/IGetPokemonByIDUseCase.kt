package com.example.mvpstudy.presentation.detail.domain.usecase.abstraction

import com.example.mvpstudy.data.remote.util.Resource
import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon
import kotlinx.coroutines.flow.Flow

interface IGetPokemonByIDUseCase {

    suspend fun execute(pokemonID: String): Flow<DetailPokemon>
}