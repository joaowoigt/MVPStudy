package com.example.mvpstudy.data.remote

import com.example.mvpstudy.data.remote.model.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonList>
}