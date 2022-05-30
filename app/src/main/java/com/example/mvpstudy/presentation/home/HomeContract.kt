package com.example.mvpstudy.presentation.home

import androidx.paging.PagingData
import com.example.mvpstudy.presentation.base.BasePresenter
import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry
import kotlinx.coroutines.flow.MutableStateFlow

object HomeContract {

    interface View {
        fun observeFlow()
    }

    interface Presenter: BasePresenter {
        fun retrieveData()
        val pokedexFlow: MutableStateFlow<PagingData<PokedexEntry>?>
    }
}
