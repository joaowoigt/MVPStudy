package com.example.mvpstudy.presentation.home.domain.usecase.concrete

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mvpstudy.data.remote.repository.IPokedexRepository
import com.example.mvpstudy.presentation.home.domain.PokedexPagingSource
import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry
import com.example.mvpstudy.presentation.home.domain.usecase.abstraction.IPokedexUseCase
import kotlinx.coroutines.flow.Flow

class PokedexUseCase(
    private val pokedexRepository: IPokedexRepository
) : IPokedexUseCase {

    override suspend fun execute(): Flow<PagingData<PokedexEntry>> {
        return Pager(
            config = PagingConfig(
                20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PokedexPagingSource(repository = pokedexRepository)
            }
        ).flow
    }

}