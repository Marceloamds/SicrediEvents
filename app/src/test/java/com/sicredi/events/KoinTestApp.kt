package com.sicredi.events

import android.app.Application
import com.sicredi.events.data.util.storage.PreferencesCache
import com.sicredi.events.presentation.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class KoinTestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinTestApp)
            PreferencesCache.init(this@KoinTestApp)
            modules(
                listOf(
                    networkingModule(),
                    viewModelModule(),
                    repositoryModule(),
                    interactorModule(),
                    resourceModule()
                )
            )
        }
    }

    internal fun loadModules(module: Module, block: () -> Unit) {
        loadKoinModules(module)
        block()
        unloadKoinModules(module)
    }
}