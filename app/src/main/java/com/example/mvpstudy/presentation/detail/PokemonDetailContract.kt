package com.example.mvpstudy.presentation.detail

import com.example.mvpstudy.presentation.base.BasePresenter
import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon
import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry
import com.example.mvpstudy.utils.FlowState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object PokemonDetailContract {

    interface View {
        fun observeDetailFlow()
        fun observeDescriptionAndEvolutionFlow()
    }

    interface Presenter: BasePresenter {
        var detailPokemon: StateFlow<FlowState<DetailPokemon>>
        val descriptionAndEvolution: StateFlow<FlowState<PokemonSpeciesEntry>>
        fun retrievePokemonData(pokemonID: String)
        fun retrievePokemonDescriptionAndEvolution(pokemonID: String)
    }
}