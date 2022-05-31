package com.example.mvpstudy.data.remote.model

import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry
import com.example.mvpstudy.utils.ENGLISH_FLAVOR_TEXT_INDEX

data class PokemonSpecies(
    val base_happiness: Int,
    val capture_rate: Int,
    val color: Color,
    val egg_groups: List<EggGroup>,
    val evolution_chain: EvolutionChain,
    val evolves_from_species: EvolvesFromSpecies,
    val flavor_text_entries: List<FlavorTextEntry>,
    val form_descriptions: List<FormDescription>,
    val forms_switchable: Boolean,
    val gender_rate: Int,
    val genera: List<Genera>,
    val generation: Generation,
    val growth_rate: GrowthRate,
    val habitat: Any,
    val has_gender_differences: Boolean,
    val hatch_counter: Int,
    val id: Int,
    val is_baby: Boolean,
    val is_legendary: Boolean,
    val is_mythical: Boolean,
    val name: String,
    val names: List<Name>,
    val order: Int,
    val pokedex_numbers: List<PokedexNumber>,
    val shape: Shape,
    val varieties: List<Variety>
) {
    fun mapToPokemonSpeciesEntry() = PokemonSpeciesEntry(
        description = this.flavor_text_entries[ENGLISH_FLAVOR_TEXT_INDEX].flavor_text,
        evolutions = this.evolves_from_species.mapToPokedexEntry()
    )
}