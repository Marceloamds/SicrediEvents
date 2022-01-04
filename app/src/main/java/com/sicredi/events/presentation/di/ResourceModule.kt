package com.sicredi.events.presentation.di

import com.sicredi.events.data.util.storage.Cache
import com.sicredi.events.data.util.storage.PreferencesCache
import com.sicredi.events.presentation.util.error.ErrorHandler
import org.koin.dsl.module

fun resourceModule() = module {

    single { ErrorHandler(get()) }
    single<Cache> {  PreferencesCache() }
}
