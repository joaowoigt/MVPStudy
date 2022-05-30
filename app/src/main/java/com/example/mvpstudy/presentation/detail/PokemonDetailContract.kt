package com.example.mvpstudy.presentation.detail

import com.example.mvpstudy.presentation.base.BasePresenter
import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon
import kotlinx.coroutines.flow.MutableStateFlow

object PokemonDetailContract {

    interface View {
        fun observeFlow()
        fun showError()
    }

    interface Presenter: BasePresenter {
        val detailPokemon: MutableStateFlow<DetailPokemon?>
        fun retrievePokemonData(pokemonID: String)
    }
}