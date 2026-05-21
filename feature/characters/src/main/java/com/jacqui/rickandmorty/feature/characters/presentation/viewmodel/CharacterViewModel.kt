package com.jacqui.rickandmorty.feature.characters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jacqui.rickandmorty.feature.characters.data.domain.CharacterResultDomain
import com.jacqui.rickandmorty.feature.characters.data.repository.CharacterRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 4:07 PM
 */

sealed class ScreenUIState<out T> {
    data class Success<T>(val data: T) : ScreenUIState<T>()

    data class Error(val message: String) : ScreenUIState<Nothing>()

    data object Loading : ScreenUIState<Nothing>()
}

class CharacterViewModel(
    private val characterRepo: CharacterRepo
) : ViewModel() {

    val characters: StateFlow<PagingData<CharacterResultDomain>> = characterRepo.getCharacters()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PagingData.empty()
        )
}
