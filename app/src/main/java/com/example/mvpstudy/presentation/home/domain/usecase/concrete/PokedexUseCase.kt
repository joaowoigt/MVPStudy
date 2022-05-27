package com.example.mvpstudy.presentation.home.domain.usecase.concrete

import com.example.mvpstudy.data.remote.repository.IPokedexRepository
import com.example.mvpstudy.data.remote.repository.PokedexRepository
import com.example.mvpstudy.presentation.home.domain.model.PokedexListEntry
import com.example.mvpstudy.presentation.home.domain.usecase.abstraction.IPokedexUseCase
import com.example.mvpstudy.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PokedexUseCase(
    private val pokedexRepository: IPokedexRepository
) : IPokedexUseCase {

    override suspend fun execute(limit: Int, offSet: Int): Flow<PokedexListEntry?> {
        return  flow {
            pokedexRepository.getPokedex(limit, offSet).collect {
                when (it) {
                    is Resource.Success -> emit(it.data?.mapToPokedexListEntry())
                    else -> {}
                }
            }
        }
    }

}