package com.example.mvpstudy.data.remote

import com.example.mvpstudy.data.remote.model.Pokemon
import com.example.mvpstudy.data.remote.model.PokemonList
import com.example.mvpstudy.data.remote.model.PokemonSpecies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.net.IDN

interface PokeService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonList>

    @GET("pokemon/{pokemonID}")
    suspend fun getPokemonById(
        @Path("pokemonID") pokemonID: String
    ): Response<Pokemon>

    @GET("pokemon-species/{pokemonID}")
    suspend fun getPokemonSpeciesById(
        @Path("pokemonID") pokemonID: String
    ): Response<PokemonSpecies>
}