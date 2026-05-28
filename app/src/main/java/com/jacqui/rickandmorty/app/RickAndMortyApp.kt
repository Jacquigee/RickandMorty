package com.jacqui.rickandmorty.app

import android.app.Application
import com.jacqui.rickandmorty.core.network.di.networkModule
import com.jacqui.rickandmorty.feature.characters.di.characterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


/**
 * Project Name: Rick and Morty
 * User        : jacqui
 * Email       : gitaujaquiline@gmail.com
 * Date        : Fri, 3/27/26
 * Time        : 4:56 PM
 */

class RickAndMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMortyApp)
            modules(
                networkModule,
                characterModule,
            )
        }
        initializeTimber()
    }

    private fun initializeTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
