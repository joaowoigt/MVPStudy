package com.example.mvpstudy.data.remote.model

data class Chain(
    val evolution_details: Any,
    val evolves_to: List<EvolvesTo>,
    val is_baby: Boolean,
    val species: Species
)