package com.example.mvpstudy.presentation.home

import androidx.lifecycle.LiveData
import com.example.mvpstudy.presentation.home.domain.model.PokedexListEntry
import com.example.mvpstudy.presentation.home.domain.usecase.abstraction.IPokedexUseCase
import com.example.mvpstudy.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

class HomePresenter(
    private val view: HomeContract.View,
    private val pokedexUseCase: IPokedexUseCase,
    override val coroutineContext: CoroutineContext,
) : HomeContract.Presenter, CoroutineScope {

    override val pokedexFlow = MutableStateFlow<PokedexListEntry?>(null)

    override fun retrieveData() {
        launch {
            withContext(Dispatchers.IO) {
                pokedexUseCase.execute(20, 0).collect {
                    when (it) {
                        is Resource.Success -> {
                            it.data?.let { pokemonList ->
                                pokedexFlow.value = pokemonList.mapToPokedexListEntry()
                            }
                        }
                        is Resource.Error -> view.networkErrorToast()
                        else -> view.unknownErrorToast()
                    }
                }
            }
        }
    }
}