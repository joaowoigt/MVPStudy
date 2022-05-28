package com.example.mvpstudy.data.remote.repository

import com.example.mvpstudy.data.remote.PokeService
import com.example.mvpstudy.data.remote.model.PokemonList
import com.example.mvpstudy.data.remote.util.Resource
import com.example.mvpstudy.data.remote.util.safeApiCall

class PokedexRepository(val pokeService: PokeService) : IPokedexRepository {

    override suspend fun getPokedex(limit: Int, offSet: Int): Resource<PokemonList> {
        return safeApiCall { pokeService.getPokemonList(limit, offSet) }
    }

}