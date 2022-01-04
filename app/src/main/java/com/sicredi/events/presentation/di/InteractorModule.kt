package com.sicredi.events.presentation.di

import com.sicredi.events.domain.interactor.GetEventsList
import com.sicredi.events.domain.interactor.EventCheckIn
import com.sicredi.events.domain.interactor.GetUserInfo
import com.sicredi.events.domain.interactor.SaveCheckInInfo
import org.koin.dsl.module

fun interactorModule() = module {

    single { EventCheckIn(get()) }

    single { GetEventsList(get()) }

    single { GetUserInfo(get()) }

    single { SaveCheckInInfo(get()) }
}