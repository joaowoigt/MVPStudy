package com.example.mvpstudy.data.remote.repository

import com.example.mvpstudy.data.remote.model.PokemonList
import com.example.mvpstudy.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IPokedexRepository {
    suspend fun getPokedex(limit: Int, offSet: Int): Resource<PokemonList>
}