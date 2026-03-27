package com.jacqui.rickandmorty.sources.remote.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 2:04 PM
 */

sealed interface NetworkResult<out T> {
    data class Error(val exception: Exception) : NetworkResult<Nothing>

    data class Success<T>(val data: T) : NetworkResult<T>
}

fun <T> Flow<T>.asResult(): Flow<NetworkResult<T>> = this
    .map<T, NetworkResult<T>> {
        NetworkResult.Success(it)
    }.catch { emit(NetworkResult.Error(Exception(it.message))) }
