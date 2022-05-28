package com.example.mvpstudy.presentation.home.domain.usecase.abstraction

import androidx.paging.PagingData
import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry
import kotlinx.coroutines.flow.Flow

interface IPokedexUseCase {

    suspend fun execute(): Flow<PagingData<PokedexEntry>>
}