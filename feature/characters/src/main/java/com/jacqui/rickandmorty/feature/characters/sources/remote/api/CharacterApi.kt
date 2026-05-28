package com.jacqui.rickandmorty.feature.characters.sources.remote.api

import com.jacqui.rickandmorty.core.common.result.NetworkResult
import com.jacqui.rickandmorty.feature.characters.sources.remote.model.CharacterDto

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 1:44 PM
 */

interface CharacterApi {
    suspend fun getCharacters(page: Int = 1): NetworkResult<CharacterDto>
}
