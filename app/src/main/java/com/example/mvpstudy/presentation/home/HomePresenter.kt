package com.example.mvpstudy.presentation.home

import com.example.mvpstudy.presentation.home.domain.usecase.abstraction.IPokedexUseCase
import com.example.mvpstudy.presentation.home.domain.usecase.concrete.PokedexUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

class HomePresenter(
    val view: HomeContract.View,
    val pokedexUseCase: IPokedexUseCase,
    override val coroutineContext: CoroutineContext,
): HomeContract.Presenter, CoroutineScope {

    override fun retrieveData() {
        launch {
            withContext(Dispatchers.IO) {
                pokedexUseCase.execute(20, 0).catch {
                    throw RuntimeException()
                }.collect {
                    view.setupAdapter(it)
                }
            }
        }
    }
}