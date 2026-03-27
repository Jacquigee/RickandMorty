package com.jacqui.rickandmorty.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jacqui.rickandmorty.data.repository.CharacterRepo
import com.jacqui.rickandmorty.data.repository.CharacterRepoImpl
import com.jacqui.rickandmorty.sources.remote.api.CharacterApi
import com.jacqui.rickandmorty.sources.remote.api.CharacterApiImpl
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 2:35 PM
 */

val characterModule =
    module {
        single {
            val loggingInterceptor = HttpLoggingInterceptor()
//            if (BuildConfig.DEBUG) {
//                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//            }

            val chuckerCollector =
                ChuckerCollector(
                    context = androidContext(),
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR,
                )

            val chuckerInterceptor =
                ChuckerInterceptor.Builder(context = androidContext())
                    .collector(chuckerCollector)
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()

            val okhttpClient =
                OkHttpClient.Builder()
                    .addInterceptor(chuckerInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()

            val contentType = "application/json".toMediaType()
            val json =
                Json {
                    ignoreUnknownKeys = true
                }
//            kt.Builder()
//                .baseUrl(
//                    "https://vast-adequately-elk.ngrok-free.app/Tende_monitoring_tool-main/",
//                )
//                .addConverterFactory(
//                    json.asConverterFactory(contentType),
//                )
//                .client(okhttpClient)
//                .build()
        }

        single<CharacterApi> { CharacterApiImpl(get()) }
    }

val repositoryModule = module {
    single<CharacterRepo> { CharacterRepoImpl(get(), get()) }
}