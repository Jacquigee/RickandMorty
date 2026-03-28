package com.jacqui.rickandmorty.sources.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jacqui.rickandmorty.data.domain.CharacterResultDomain
import com.jacqui.rickandmorty.data.mappers.toDomain
import com.jacqui.rickandmorty.sources.remote.api.CharacterApi
import com.jacqui.rickandmorty.sources.remote.utils.NetworkResult

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 11:11 PM
 */

class CharacterPagingSource(
    private val characterApi: CharacterApi
) : PagingSource<Int, CharacterResultDomain>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResultDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResultDomain> {
        return try {
            val page = params.key ?: 1
            val response = characterApi.getCharacters(page = page)
            val characters = when (response) {
                is NetworkResult.Success -> response.data.results.map { it.toDomain() }
                is NetworkResult.Error -> return LoadResult.Error(response.exception)
            }
            LoadResult.Page(
                data = characters,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response is NetworkResult.Success && response.data.info.next != null) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}