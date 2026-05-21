package com.jacqui.rickandmorty.feature.characters.data.repository

import androidx.paging.PagingData
import com.jacqui.rickandmorty.feature.characters.data.domain.CharacterResultDomain
import kotlinx.coroutines.flow.Flow

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 2:54 PM
 */

interface CharacterRepo {
    fun getCharacters(): Flow<PagingData<CharacterResultDomain>>
}
