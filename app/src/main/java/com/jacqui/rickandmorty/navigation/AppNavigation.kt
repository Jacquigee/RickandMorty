package com.jacqui.rickandmorty.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.jacqui.rickandmorty.presentation.CharacterScreen
import com.jacqui.rickandmorty.presentation.CharacterDetailScreen

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Sat, 3/28/26
 * Time        : 8:44 AM
 */

@Composable
fun AppNavigation() {
    val backstack = remember { mutableStateListOf<Screen>(Screen.CharacterList) }
    MaterialTheme {
        NavDisplay(
            backStack = backstack,
            onBack = { backstack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    Screen.CharacterList -> {
                        NavEntry(key) { CharacterScreen(
                            onCharacterClick = { character ->
                                backstack.add(Screen.CharacterDetails(id = character.id))
                            }
                        ) }
                    }

                    is Screen.CharacterDetails -> {
                        NavEntry(key) {
                            CharacterDetailScreen(
                                characterId = key.id,
                                onBack = { backstack.removeLastOrNull() }
                            )
                        }
                    }

                    else ->
                        NavEntry(key) {}

                }

            },

            )
    }
}