package com.jacqui.rickandmorty.sources.remote.utils

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 1:54 PM
 */

sealed class Endpoints(private val path: String) {
    val charactersUrl: String
        get() = buildString {
            append("https://rickandmortyapi.com/api")
            append(path)
        }

    data object Characters : Endpoints("/character")
}
