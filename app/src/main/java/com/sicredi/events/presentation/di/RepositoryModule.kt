package com.sicredi.events.presentation.di

import com.sicredi.events.data.repository.DefaultEventRepository
import com.sicredi.events.data.repository.DefaultUserInfoRepository
import com.sicredi.events.domain.boundary.EventRepository
import com.sicredi.events.domain.boundary.UserInfoRepository
import org.koin.dsl.module

fun repositoryModule() = module {
    single<EventRepository> { DefaultEventRepository(get()) }
    single<UserInfoRepository> { DefaultUserInfoRepository(get()) }
}