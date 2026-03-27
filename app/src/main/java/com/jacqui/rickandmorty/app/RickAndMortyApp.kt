package com.jacqui.rickandmorty.app

import android.app.Application
import com.jacqui.rickandmorty.di.characterModule
import com.jacqui.rickandmorty.di.repositoryModule
import com.jacqui.rickandmorty.di.viewmodelModule
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
                characterModule,
                repositoryModule,
                viewmodelModule,
            )
        }
        initializeTimber()
    }

    private fun initializeTimber() {
        Timber.plant(Timber.DebugTree())
    }
}