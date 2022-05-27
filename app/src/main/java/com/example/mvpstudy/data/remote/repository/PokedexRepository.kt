package com.example.mvpstudy.data.remote.repository

import com.example.mvpstudy.data.remote.PokeService
import com.example.mvpstudy.data.remote.model.Pokemon
import com.example.mvpstudy.data.remote.model.PokemonList
import com.example.mvpstudy.data.remote.util.safeApiCall
import com.example.mvpstudy.presentation.home.domain.model.PokedexListEntry
import com.example.mvpstudy.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokedexRepository(val pokeService: PokeService) : IPokedexRepository {

    override fun getPokedex(limit: Int, offSet: Int): Flow<Resource<PokemonList>> {
        return flow {
            val result = safeApiCall { pokeService.getPokemonList(limit, offSet) }
            emit(result)
        }
    }

}