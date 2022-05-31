package com.example.mvpstudy.data.remote.model

data class EvolvesTo(
    val evolution_details: List<EvolutionDetail>,
    val evolves_to: List<Any>,
    val is_baby: Boolean,
    val species: Species
)