package com.example.mvpstudy.presentation.detail.domain.usecase.concret

import android.util.Log
import com.example.mvpstudy.data.remote.repository.IPokedexRepository
import com.example.mvpstudy.data.remote.util.Resource
import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry
import com.example.mvpstudy.presentation.detail.domain.usecase.abstraction.IGetDescriptionAndEvolutionUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonDescriptionAndEvolutionUseCase(
    private val repository: IPokedexRepository
): IGetDescriptionAndEvolutionUseCase {
    override fun execute(pokemonID: String): Flow<PokemonSpeciesEntry> {
        return flow {
            when(val result = repository.getPokemonSpeciesById(pokemonID)) {
                is Resource.Success -> emit(result.data.mapToPokemonSpeciesEntry())
                is Resource.Error -> Log.d("UseCase description", result.message)
            }
        }
    }
}