package com.jacqui.rickandmorty.data.utils

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 2:57 PM
 */


sealed class DataResult<out T> {
    data class Success<out T>(val data: T) : DataResult<T>()

    data class Error(val error: String) : DataResult<Nothing>()
}