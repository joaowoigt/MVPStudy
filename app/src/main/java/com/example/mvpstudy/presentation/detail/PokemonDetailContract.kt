package com.example.mvpstudy.presentation.detail

import com.example.mvpstudy.presentation.base.BasePresenter
import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon
import com.example.mvpstudy.utils.FlowState
import kotlinx.coroutines.flow.MutableStateFlow

object PokemonDetailContract {

    interface View {
        fun observeFlow()
    }

    interface Presenter: BasePresenter {
        val detailPokemon: MutableStateFlow<FlowState<DetailPokemon>>
        fun retrievePokemonData(pokemonID: String)
        fun retrievePokemonEvolutionChain(pokemonID: String)
    }
}