package com.sicredi.events.presentation.di

import com.sicredi.events.domain.interactor.GetEventsList
import com.sicredi.events.domain.interactor.GetEventDetail
import com.sicredi.events.domain.interactor.EventCheckIn
import org.koin.dsl.module

fun interactorModule() = module {
    single {
        GetEventDetail(get())
    }

    single {
        EventCheckIn(get())
    }

    single {
        GetEventsList(get())
    }
}