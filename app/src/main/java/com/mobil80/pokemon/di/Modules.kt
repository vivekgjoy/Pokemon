package com.mobil80.pokemon.di

import com.mobil80.pokemon.core.Constants.BASE_URL
import com.mobil80.pokemon.data.network.ApiCalls
import com.mobil80.pokemon.data.reposiitory.PokemonRepository
import com.mobil80.pokemon.presentation.viewmodel.PokemonDetailViewModel
import com.mobil80.pokemon.presentation.viewmodel.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule: Module = module {

    single { getClient() }

    single { PokemonRepository(get()) }

    viewModel { PokemonDetailViewModel(get()) }
    viewModel { PokemonListViewModel(get()) }
}

fun getClient(): ApiCalls {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(ApiCalls::class.java)
}