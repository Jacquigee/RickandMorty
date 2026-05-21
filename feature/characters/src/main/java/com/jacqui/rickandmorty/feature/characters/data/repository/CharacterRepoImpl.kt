package com.jacqui.rickandmorty.feature.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jacqui.rickandmorty.feature.characters.data.domain.CharacterResultDomain
import com.jacqui.rickandmorty.feature.characters.sources.remote.CharacterPagingSource
import com.jacqui.rickandmorty.feature.characters.sources.remote.api.CharacterApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 2:57 PM
 */

class CharacterRepoImpl(
    private val characterApi: CharacterApi,
    private val dispatcher: CoroutineDispatcher
) : CharacterRepo {
    override fun getCharacters(): Flow<PagingData<CharacterResultDomain>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CharacterPagingSource(characterApi) }
        ).flow.flowOn(dispatcher)
    }
}
