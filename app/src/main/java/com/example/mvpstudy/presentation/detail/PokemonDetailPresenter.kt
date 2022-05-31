package com.example.mvpstudy.presentation.detail

import com.example.mvpstudy.presentation.detail.domain.model.DetailPokemon
import com.example.mvpstudy.presentation.detail.domain.model.PokemonSpeciesEntry
import com.example.mvpstudy.presentation.detail.domain.usecase.abstraction.IGetDescriptionAndEvolutionUseCase
import com.example.mvpstudy.presentation.detail.domain.usecase.abstraction.IGetPokemonByIDUseCase
import com.example.mvpstudy.utils.FlowState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

class PokemonDetailPresenter(
    var view: PokemonDetailContract.View?,
    private val getPokemonByIDUseCase: IGetPokemonByIDUseCase,
    private val getPokemonDescriptionAndEvolutionUseCase: IGetDescriptionAndEvolutionUseCase,
    override val coroutineContext: CoroutineContext,
) : PokemonDetailContract.Presenter, CoroutineScope {


    private val _detailPokemon = MutableStateFlow<FlowState<DetailPokemon>>(FlowState.Initial)
    override var detailPokemon: StateFlow<FlowState<DetailPokemon>> = _detailPokemon.asStateFlow()

    private val _descriptionAndEvolution = MutableStateFlow<FlowState<PokemonSpeciesEntry>>(FlowState.Initial)
    override val descriptionAndEvolution: StateFlow<FlowState<PokemonSpeciesEntry>> = _descriptionAndEvolution.asStateFlow()


    override fun retrievePokemonData(pokemonID: String) {
        launch {
            withContext(Dispatchers.IO) {
                view?.observeDetailFlow()
                _detailPokemon.value = FlowState.Loading
                getPokemonByIDUseCase.execute(pokemonID).catch {
                    _detailPokemon.value = FlowState.Error("Loading error...")
                }.collect {
                    _detailPokemon.value = FlowState.Success(it)
                }
            }
        }
    }

    override fun retrievePokemonDescriptionAndEvolution(pokemonID: String) {
        launch {
            withContext(Dispatchers.IO) {
                view?.observeDescriptionAndEvolutionFlow()
                _descriptionAndEvolution.value = FlowState.Loading
                getPokemonDescriptionAndEvolutionUseCase.execute(pokemonID).catch {
                    _descriptionAndEvolution.value = FlowState.Error("Loading Error...")
                }.collect {
                    _descriptionAndEvolution.value = FlowState.Success(it)
                }
            }
        }
    }

    override fun onDestroy() {
        this.view = null
    }
}