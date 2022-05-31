package com.example.mvpstudy.di

import com.example.mvpstudy.data.remote.PokeService
import com.example.mvpstudy.data.remote.repository.IPokedexRepository
import com.example.mvpstudy.data.remote.repository.PokedexRepository
import com.example.mvpstudy.presentation.detail.PokemonDetailContract
import com.example.mvpstudy.presentation.detail.PokemonDetailPresenter
import com.example.mvpstudy.presentation.detail.domain.usecase.abstraction.IGetPokemonByIDUseCase
import com.example.mvpstudy.presentation.detail.domain.usecase.abstraction.IGetPokemonEvolutionChainUseCase
import com.example.mvpstudy.presentation.detail.domain.usecase.concret.GetPokemonByIDUseCase
import com.example.mvpstudy.presentation.detail.domain.usecase.concret.GetPokemonEvolutionChainUseCase
import com.example.mvpstudy.presentation.home.HomeContract
import com.example.mvpstudy.presentation.home.HomePresenter
import com.example.mvpstudy.presentation.home.domain.usecase.abstraction.IPokedexUseCase
import com.example.mvpstudy.presentation.home.domain.usecase.concrete.PokedexUseCase
import com.example.mvpstudy.presentation.login.LoginContract
import com.example.mvpstudy.presentation.login.LoginPresenter
import com.example.mvpstudy.presentation.login.domain.ILoginUseCase
import com.example.mvpstudy.presentation.login.domain.LoginUseCase
import com.example.mvpstudy.utils.BASE_API_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext


val MainModule = module {

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_API_URL)
            .build()
    }

    single { get<Retrofit>().create(PokeService::class.java) }

    single<IPokedexRepository> { PokedexRepository(get()) }

    factory<LoginContract.Presenter> { LoginPresenter(get(), get()) }

    factory<HomeContract.Presenter> { HomePresenter(get(), get(), get()) }

    factory<PokemonDetailContract.Presenter> { PokemonDetailPresenter(get(), get(), get(), get()) }

    single<ILoginUseCase> { LoginUseCase() }

    single<IPokedexUseCase> { PokedexUseCase(get()) }

    single<IGetPokemonByIDUseCase> { GetPokemonByIDUseCase(get()) }

    single<IGetPokemonEvolutionChainUseCase> { GetPokemonEvolutionChainUseCase(get()) }

    factory<CoroutineContext> { Job() + Dispatchers.IO }

}