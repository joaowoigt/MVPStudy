package com.example.mvpstudy.presentation.detail

import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon
import com.example.mvpstudy.presentation.detail.domain.usecase.abstraction.IGetPokemonByIDUseCase
import com.example.mvpstudy.utils.FlowState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
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
                view?.observeFlow()
                detailPokemon.value = FlowState.Loading
                getPokemonByIDUseCase.execute(pokemonID).catch {
                    detailPokemon.value = FlowState.Error("Loading error...")
                }.collect {
                    detailPokemon.value = FlowState.Success(it)
                }
            }
        }
    }

    override fun onDestroy() {
        this.view = null
    }
}