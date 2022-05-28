package com.example.mvpstudy.presentation.home

import androidx.paging.PagingData
import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry
import com.example.mvpstudy.presentation.home.domain.usecase.abstraction.IPokedexUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class HomePresenter(
    private val view: HomeContract.View,
    private val pokedexUseCase: IPokedexUseCase,
    override val coroutineContext: CoroutineContext,
) : HomeContract.Presenter, CoroutineScope {

    override val pokedexFlow = MutableStateFlow<PagingData<PokedexEntry>?>(null)

    override fun retrieveData() {
        launch {
            withContext(Dispatchers.IO) {
                pokedexUseCase.execute().collect {
                    pokedexFlow.value = it
                    view.observeFlow()
                }
            }
        }
    }

}