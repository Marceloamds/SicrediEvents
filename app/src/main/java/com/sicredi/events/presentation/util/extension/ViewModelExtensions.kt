package com.sicredi.events.presentation.util.extension

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sicredi.events.presentation.util.navigation.NavData
import com.sicredi.events.presentation.util.placeholder.Placeholder
import com.sicredi.events.presentation.util.placeholder.types.Hide
import com.sicredi.events.presentation.util.placeholder.types.Loading
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun ViewModel.launchDataLoad(
    onFailure: (Throwable) -> Unit = {},
    onPlaceholder: (Placeholder) -> Unit = {},
    shouldLoad: Boolean = true,
    block: suspend () -> Unit
): Job {
    return viewModelScope.launch {
        try {
            if (shouldLoad) onPlaceholder(Loading())
            block()
        } catch (error: Throwable) {
            onFailure(error)
        } finally {
            onPlaceholder(Hide())
        }
    }
}

fun ViewModel.goTo(navData: NavData, liveData: MutableLiveData<NavData>) {
    liveData.postValue(navData)
}