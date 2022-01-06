package com.sicredi.events.presentation.di

import com.sicredi.events.presentation.view.events.details.EventDetailsViewModel
import com.sicredi.events.presentation.view.events.list.ListEventsViewModel
import com.sicredi.events.presentation.view.splash.SplashViewModel
import com.sicredi.events.presentation.view.user.info.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel {
        ListEventsViewModel(get(), get())
    }

    viewModel {
        EventDetailsViewModel(get(), get(), get())
    }

    viewModel {
        UserInfoViewModel(get())
    }

    viewModel { SplashViewModel() }
}