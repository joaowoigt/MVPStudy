package com.example.mvpstudy.presentation.home.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mvpstudy.data.remote.repository.IPokedexRepository
import com.example.mvpstudy.data.remote.util.Resource
import com.example.mvpstudy.presentation.home.domain.model.PokedexEntry

class PokedexPagingSource(private val repository: IPokedexRepository) :
    PagingSource<Int, PokedexEntry>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokedexEntry> {
        try {
            return when (val response = repository.getPokedex(limit = 20, offSet = params.key ?: 0)) {
                is Resource.Success -> {
                    LoadResult.Page(
                        data = response.data.mapToPokedexListEntry().pokedex,
                        prevKey = params.key,
                        nextKey = if (response.data.mapToPokedexListEntry().pokedex.isEmpty()) {
                            null
                        } else {
                            params.key?.plus(20) ?: 20
                        }
                    )
                }
                is Resource.Error -> {
                    LoadResult.Error(RuntimeException())
                }
            }
        } catch (exception: Throwable) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokedexEntry>): Int? {
        return state.anchorPosition?.let { anchorPostion ->
            state.closestPageToPosition(anchorPostion)?.prevKey?.plus(20)
                ?: state.closestPageToPosition(anchorPostion)?.nextKey?.minus(20)
        }
    }
}