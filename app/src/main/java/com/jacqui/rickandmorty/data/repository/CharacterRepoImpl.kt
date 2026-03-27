package com.jacqui.rickandmorty.data.repository

import com.jacqui.rickandmorty.data.domain.CharacterDomain
import com.jacqui.rickandmorty.data.mappers.toDomain
import com.jacqui.rickandmorty.data.utils.DataResult
import com.jacqui.rickandmorty.sources.remote.api.CharacterApi
import com.jacqui.rickandmorty.sources.remote.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

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
    override suspend fun getCharacters(): DataResult<List<CharacterDomain>> =
        withContext(dispatcher) {
            when (val response = characterApi.getCharacters()) {
                is NetworkResult.Error -> DataResult.Error(error = response.exception.message.toString())
                is NetworkResult.Success -> {
                    val data = response.data.map { it.toDomain() }
                    DataResult.Success(data = data)
                }
            }


        }
}