package com.jacqui.rickandmorty.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jacqui.rickandmorty.data.domain.CharacterDomain
import com.jacqui.rickandmorty.data.repository.CharacterRepo
import com.jacqui.rickandmorty.data.utils.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

data class CharacterUiState(
    val characters: ScreenUIState<List<CharacterDomain>> = ScreenUIState.Loading,
)

class CharacterViewModel(
    private val characterRepo: CharacterRepo
) : ViewModel() {

    private val _characterUiState = MutableStateFlow(CharacterUiState())
    val characterUiState: StateFlow<CharacterUiState> = _characterUiState

    private fun fetchCharacters() {
        viewModelScope.launch {
            when (val response = characterRepo.getCharacters()) {
                is DataResult.Error -> {
                    _characterUiState.update { it.copy(characters = ScreenUIState.Error(response.error)) }
                }

                is DataResult.Success -> {
                    _characterUiState.update { it.copy(characters = ScreenUIState.Success(response.data)) }
                }
            }
        }
    }
}
