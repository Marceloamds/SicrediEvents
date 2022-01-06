package com.sicredi.events.presentation.di

import com.sicredi.events.domain.use_case.EventCheckIn
import com.sicredi.events.domain.use_case.GetEventsList
import com.sicredi.events.domain.use_case.GetUserInfo
import com.sicredi.events.domain.use_case.SaveCheckInInfo
import org.koin.dsl.module

fun useCaseModule() = module {
    single { EventCheckIn(get()) }

    single { GetEventsList(get()) }

    single { GetUserInfo(get()) }

    single { SaveCheckInInfo(get()) }
}