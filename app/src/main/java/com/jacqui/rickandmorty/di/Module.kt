package com.jacqui.rickandmorty.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jacqui.rickandmorty.BuildConfig
import com.jacqui.rickandmorty.data.repository.CharacterRepo
import com.jacqui.rickandmorty.data.repository.CharacterRepoImpl
import com.jacqui.rickandmorty.sources.remote.api.CharacterApi
import com.jacqui.rickandmorty.sources.remote.api.CharacterApiImpl
import com.jacqui.rickandmorty.view.viewmodel.CharacterViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
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
            if (BuildConfig.DEBUG) {
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            }

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

        single<CharacterApi> { CharacterApiImpl(get()) }
    }

val repositoryModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<CharacterRepo> { CharacterRepoImpl(get(), get()) }
}

val viewmodelModule = module {
    viewModelOf(::CharacterViewModel)
}
