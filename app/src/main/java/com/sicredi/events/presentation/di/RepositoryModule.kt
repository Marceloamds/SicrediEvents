package com.sicredi.events.presentation.di

import com.sicredi.events.data.repository.DefaultEventRepository
import com.sicredi.events.domain.boundary.EventRepository
import org.koin.dsl.module

fun repositoryModule() = module {
    single {
        DefaultEventRepository(get()) as EventRepository
    }
}