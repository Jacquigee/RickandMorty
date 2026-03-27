package com.jacqui.rickandmorty.sources.remote.utils

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.serialization.JsonConvertException
import kotlinx.io.IOException
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.SerializationException
import timber.log.Timber
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 2:05 PM
 */

object ErrorMessages {
    const val NETWORK_ERROR_EXCEPTION = "Please check your internet connection."
}

@OptIn(ExperimentalSerializationApi::class)
suspend fun <T> safeApiCall(errorMessage: String = "", block: suspend () -> T): NetworkResult<T> =
    try {
        val data =
            block.invoke() // Tries to run the lambda and assign the result to data.
        // If it fails it Throws an exception.

        NetworkResult.Success(data = data)
    } catch (e: JsonConvertException) {
        Timber.e(e)
        NetworkResult.Error(Exception("Unable to parse server response"))
    } catch (e: MissingFieldException) {
        Timber.e(e)
        NetworkResult.Error(Exception("Missing required fields in server response"))
    } catch (e: SerializationException) {
        Timber.e(e)
        NetworkResult.Error(Exception("Unable to parse server response"))
    } catch (e: UnknownHostException) {
        Timber.e("Please check your internet exception")
        NetworkResult.Error(Exception(ErrorMessages.NETWORK_ERROR_EXCEPTION))
    } catch (e: IOException) {
        Timber.e(e)
        NetworkResult.Error(Exception(e.localizedMessage))
    } catch (e: ClientRequestException) {
        Timber.e(e)
        NetworkResult.Error(Exception(errorMessage))
    } catch (e: ServerResponseException) {
        Timber.e(e)
        NetworkResult.Error(Exception("Server error occurred"))
    } catch (e: TimeoutException) {
        Timber.e(e)
        NetworkResult.Error(Exception("Request timed out"))
    } catch (e: CancellationException) {
        Timber.e(e)
        throw e
    }
