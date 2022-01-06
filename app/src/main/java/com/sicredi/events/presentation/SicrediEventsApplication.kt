package com.sicredi.events.presentation

import android.app.Application
import com.sicredi.events.data.util.storage.PreferencesCache
import com.sicredi.events.presentation.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SicrediEventsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SicrediEventsApplication)
            PreferencesCache.init(this@SicrediEventsApplication)
            modules(
                listOf(
                    networkingModule(),
                    viewModelModule(),
                    repositoryModule(),
                    useCaseModule(),
                    resourceModule()
                )
            )
        }
    }
}