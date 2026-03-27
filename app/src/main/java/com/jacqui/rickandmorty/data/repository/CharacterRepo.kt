package com.jacqui.rickandmorty.data.repository

import com.jacqui.rickandmorty.data.domain.CharacterDomain
import com.jacqui.rickandmorty.data.utils.DataResult
import kotlinx.coroutines.flow.Flow

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 2:54 PM
 */

interface CharacterRepo {
    suspend fun getCharacters(): DataResult<List<CharacterDomain>>
}