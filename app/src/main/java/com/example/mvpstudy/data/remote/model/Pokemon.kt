package com.example.mvpstudy.data.remote.model

import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon

data class Pokemon(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
) {
    fun mapToDetailPokemon() = DetailPokemon(
        abilities = this.abilities,
        base_experience = this.base_experience,
        forms = this.forms,
        game_indices = this.game_indices,
        height = this.height,
        held_items = this.held_items,
        id = this.id,
        is_default = this.is_default,
        location_area_encounters = this.location_area_encounters,
        moves = this.moves,
        name = this.name,
        order = this.order,
        past_types = this.past_types,
        species = this.species,
        sprites = this.sprites,
        stats = this.stats,
        types = this.types,
        weight = this.weight
    )
}