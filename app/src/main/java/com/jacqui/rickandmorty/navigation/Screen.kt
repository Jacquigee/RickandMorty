package com.jacqui.rickandmorty.navigation

import androidx.navigation3.runtime.NavKey

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Sat, 3/28/26
 * Time        : 7:53 AM
 */

sealed class Screen : NavKey {
    data object CharacterList : Screen()
    data class CharacterDetails (val id: Int) : Screen()
}