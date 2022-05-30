package com.example.mvpstudy.data.remote.repository

import com.example.mvpstudy.data.remote.model.Pokemon
import com.example.mvpstudy.data.remote.model.PokemonList
import com.example.mvpstudy.data.remote.util.Resource

interface IPokedexRepository {
    suspend fun getPokedex(limit: Int, offSet: Int): Resource<PokemonList>
    suspend fun getPokemonById(pokemonNumber: Int): Resource<Pokemon>
}