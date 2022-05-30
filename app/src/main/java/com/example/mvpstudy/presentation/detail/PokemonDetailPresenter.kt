package com.example.mvpstudy.presentation.detail

import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon
import com.example.mvpstudy.presentation.detail.domain.usecase.abstraction.IGetPokemonByIDUseCase
import com.example.mvpstudy.utils.FlowState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PokemonDetailPresenter(
    var view: PokemonDetailContract.View?,
    val getPokemonByIDUseCase: IGetPokemonByIDUseCase,
    override val coroutineContext: CoroutineContext
) : PokemonDetailContract.Presenter, CoroutineScope {


    override val detailPokemon = MutableStateFlow<FlowState<DetailPokemon>>(FlowState.Initial)

    override fun retrievePokemonData(pokemonID: String) {
        launch {
            withContext(Dispatchers.IO) {
                getPokemonByIDUseCase.execute(pokemonID).collect {
                    detailPokemon.value = FlowState.Success(it)
                    view?.observeFlow()
                }
            }
        }
    }

    override fun onDestroy() {
        this.view = null
    }
}