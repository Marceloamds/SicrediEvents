package com.sicredi.events.presentation.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sicredi.events.presentation.util.extension.launchDataLoad
import com.sicredi.events.presentation.util.navigation.NavData
import com.sicredi.events.presentation.view.events.list.ListEventsNavData
import kotlinx.coroutines.delay

class SplashViewModel : ViewModel() {

    val goTo: LiveData<NavData> get() = _goTo
    private val _goTo by lazy { MutableLiveData<NavData>() }

    init {
        launchDataLoad {
            delay(SPLASH_DELAY)
            _goTo.value = ListEventsNavData()
        }
    }

    companion object {
        const val SPLASH_DELAY = 2000L
    }
}