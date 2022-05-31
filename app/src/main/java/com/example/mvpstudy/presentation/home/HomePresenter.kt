package com.example.mvpstudy.presentation.home

import androidx.paging.PagingData
import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry
import com.example.mvpstudy.presentation.home.domain.usecase.abstraction.IPokedexUseCase
import com.example.mvpstudy.utils.FlowState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class HomePresenter(
    private var view: HomeContract.View?,
    private val pokedexUseCase: IPokedexUseCase,
    override val coroutineContext: CoroutineContext,
) : HomeContract.Presenter, CoroutineScope {

    override val pokedexFlow =
        MutableStateFlow<FlowState<PagingData<PokedexEntry>>>(FlowState.Initial)

    override fun onDestroy() {
        this.view = null
    }

    override fun retrieveData() {
        launch {
            withContext(Dispatchers.IO) {
                view?.observeFlow()
                pokedexFlow.value = FlowState.Loading
                pokedexUseCase.execute()
                    .catch {
                    pokedexFlow.value = FlowState.Error("Loading error...")
                }
                    .collect {
                        pokedexFlow.value = FlowState.Success(it)

                    }
            }
        }
    }

}