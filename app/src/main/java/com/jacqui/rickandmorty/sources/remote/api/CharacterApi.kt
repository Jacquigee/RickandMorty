package com.jacqui.rickandmorty.sources.remote.api

import com.jacqui.rickandmorty.sources.remote.model.CharacterDto
import com.jacqui.rickandmorty.sources.remote.utils.NetworkResult

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 1:44 PM
 */

interface CharacterApi {
    suspend fun getCharacters(): NetworkResult<List<CharacterDto>>
}