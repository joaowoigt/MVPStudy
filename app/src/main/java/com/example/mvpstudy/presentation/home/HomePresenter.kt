package com.example.mvpstudy.presentation.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.palette.graphics.Palette
import com.example.mvpstudy.presentation.home.domain.usecase.abstraction.IPokedexUseCase
import com.example.mvpstudy.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

class HomePresenter(
    val view: HomeContract.View,
    val pokedexUseCase: IPokedexUseCase,
    override val coroutineContext: CoroutineContext,
) : HomeContract.Presenter, CoroutineScope {


    override fun retrieveData() {
        launch {
            withContext(Dispatchers.IO) {
                pokedexUseCase.execute(20, 0).collect {
                    when (it) {
                        is Resource.Success -> {
                            it.data?.let { pokemonList ->
                                view.setupAdapter(pokemonList.mapToPokedexListEntry())
                            }
                        }
                        is Resource.Error -> view.networkErrorToast()
                        else -> view.unknownErrorToast()
                    }
                }
            }
        }
    }

    override fun calcDominantColor(drawable: Drawable): Int? {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        return Palette.from(bitmap).generate().dominantSwatch?.rgb
    }

}