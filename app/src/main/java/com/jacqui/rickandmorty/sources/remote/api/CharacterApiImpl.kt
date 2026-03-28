package com.jacqui.rickandmorty.sources.remote.api

import com.jacqui.rickandmorty.sources.remote.model.CharacterDto
import com.jacqui.rickandmorty.sources.remote.utils.Endpoints
import com.jacqui.rickandmorty.sources.remote.utils.NetworkResult
import com.jacqui.rickandmorty.sources.remote.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import timber.log.Timber

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 2:07 PM
 */

class CharacterApiImpl(val client: HttpClient) : CharacterApi {
    override suspend fun getCharacters(page: Int): NetworkResult<CharacterDto> =
        safeApiCall(errorMessage = "Failed to fetch characters") {
            val response = client.get {
                url(urlString = Endpoints.Characters.charactersUrl)
                parameter("page", page)
            }
            Timber.d("CharacterApiImpl getCharacters: $response")
            response.body()
        }

}