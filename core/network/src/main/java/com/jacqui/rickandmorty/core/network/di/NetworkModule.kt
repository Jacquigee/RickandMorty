package com.jacqui.rickandmorty.core.network.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        // Note: BuildConfig.DEBUG might need to be passed or handled differently in core module
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val chuckerCollector = ChuckerCollector(
            context = androidContext(),
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR,
        )

        val chuckerInterceptor = ChuckerInterceptor.Builder(context = androidContext())
            .collector(chuckerCollector)
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

        HttpClient(OkHttp) {
            engine {
                addInterceptor(loggingInterceptor)
                addInterceptor(chuckerInterceptor)
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}
