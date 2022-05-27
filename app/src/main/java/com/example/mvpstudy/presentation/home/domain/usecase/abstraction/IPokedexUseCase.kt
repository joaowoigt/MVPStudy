package com.example.mvpstudy.presentation.home.domain.usecase.abstraction

import com.example.mvpstudy.data.remote.repository.PokedexRepository
import com.example.mvpstudy.presentation.home.domain.model.PokedexListEntry
import kotlinx.coroutines.flow.Flow

interface IPokedexUseCase {

    suspend fun execute(limit: Int, offSet: Int): Flow<PokedexListEntry?>
}