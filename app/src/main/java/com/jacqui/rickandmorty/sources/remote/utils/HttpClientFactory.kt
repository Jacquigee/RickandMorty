package com.jacqui.rickandmorty.sources.remote.utils

import android.R.attr.level
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 2:02 PM
 */

const val REQUEST_TIMEOUT = 600_000L
const val CONNECT_TIMEOUT = 10_000L
const val SOCKET_TIMEOUT = 600_000L

class HttpClientFactory {
    fun create(engine: HttpClientEngine) = HttpClient(engine) {
        install(Logging) {
            level = LogLevel.BODY
        }
        install(ContentNegotiation) {
            json(
                Json {
                    coerceInputValues = true
                    ignoreUnknownKeys = true
                },
            )
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = REQUEST_TIMEOUT // 10 minutes
            connectTimeoutMillis = CONNECT_TIMEOUT // 10 seconds to establish a connection
            socketTimeoutMillis = SOCKET_TIMEOUT // 10 minutes for socket inactivity
        }
    }
}