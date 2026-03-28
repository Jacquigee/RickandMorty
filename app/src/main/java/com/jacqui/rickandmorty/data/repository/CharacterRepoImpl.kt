package com.jacqui.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jacqui.rickandmorty.data.domain.CharacterDomain
import com.jacqui.rickandmorty.data.domain.CharacterResultDomain
import com.jacqui.rickandmorty.sources.remote.CharacterPagingSource
import com.jacqui.rickandmorty.sources.remote.api.CharacterApi
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
