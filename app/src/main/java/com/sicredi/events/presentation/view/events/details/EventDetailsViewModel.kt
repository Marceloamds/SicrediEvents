package com.sicredi.events.presentation.view.events.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.domain.interactor.EventCheckIn
import com.sicredi.events.presentation.util.base.BaseViewModel

class EventDetailsViewModel(
    private val eventCheckIn: EventCheckIn
) : BaseViewModel() {

    val shareEvent: LiveData<Event> get() = _shareEvent
    private val _shareEvent by lazy { MutableLiveData<Event>() }

    fun onShareClicked(event: Event?) {
        _shareEvent.value = event
    }
}