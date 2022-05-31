package com.example.mvpstudy.presentation.detail.domain.usecase.concret

import android.util.Log
import com.example.mvpstudy.data.remote.repository.IPokedexRepository
import com.example.mvpstudy.data.remote.util.Resource
import com.example.mvpstudy.presentation.detail.domain.model.EvolutionChainEntry
import com.example.mvpstudy.presentation.detail.domain.usecase.abstraction.IGetPokemonEvolutionChainUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonEvolutionChainUseCase(private val repository: IPokedexRepository): IGetPokemonEvolutionChainUseCase {

    override suspend fun execute(pokemonID: String): Flow<EvolutionChainEntry> {
        return flow {
            when(val result = repository.getPokemonEvolutionChain(pokemonID)){
                is Resource.Success -> emit(result.data.mapToEvolutionChainEntry())
                is Resource.Error -> Log.d("UseCase", result.message)
            }
        }
    }
}