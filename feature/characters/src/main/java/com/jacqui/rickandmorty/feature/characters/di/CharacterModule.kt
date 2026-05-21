package com.jacqui.rickandmorty.feature.characters.di

import com.jacqui.rickandmorty.feature.characters.data.repository.CharacterRepo
import com.jacqui.rickandmorty.feature.characters.data.repository.CharacterRepoImpl
import com.jacqui.rickandmorty.feature.characters.presentation.viewmodel.CharacterViewModel
import com.jacqui.rickandmorty.feature.characters.sources.remote.api.CharacterApi
import com.jacqui.rickandmorty.feature.characters.sources.remote.api.CharacterApiImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val characterModule = module {
    single<CharacterApi> { CharacterApiImpl(get()) }
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<CharacterRepo> { CharacterRepoImpl(get(), get()) }
    viewModelOf(::CharacterViewModel)
}
