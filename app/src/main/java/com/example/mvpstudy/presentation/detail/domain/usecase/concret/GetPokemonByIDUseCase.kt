package com.example.mvpstudy.presentation.detail.domain.usecase.concret

import android.util.Log
import com.example.mvpstudy.data.remote.repository.IPokedexRepository
import com.example.mvpstudy.data.remote.util.Resource
import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon
import com.example.mvpstudy.presentation.detail.domain.usecase.abstraction.IGetPokemonByIDUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonByIDUseCase(
    private val repository: IPokedexRepository
): IGetPokemonByIDUseCase {


    override suspend fun execute(pokemonID: String): Flow<DetailPokemon> {
        return flow {
            when(val result = repository.getPokemonById(pokemonID)) {
                is Resource.Success -> emit(result.data.mapToDetailPokemon())
                is Resource.Error -> Log.d("useCase", result.message)
            }
        }
    }
}