package com.sicredi.events.presentation.view.events.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.domain.entity.user.UserInfo
import com.sicredi.events.domain.interactor.EventCheckIn
import com.sicredi.events.domain.interactor.GetUserInfo
import com.sicredi.events.presentation.util.communication.SingleLiveEvent
import com.sicredi.events.presentation.util.error.ErrorHandler
import com.sicredi.events.presentation.util.extension.launchDataLoad
import com.sicredi.events.presentation.util.navigation.NavData
import com.sicredi.events.presentation.view.events.check_in.EventCheckInNavData

class EventDetailsViewModel(
    private val eventCheckIn: EventCheckIn,
    private val errorHandler: ErrorHandler,
    private val getUserInfo: GetUserInfo
) : ViewModel() {

    val shareEvent: LiveData<Event> get() = _shareEvent
    val eventDetail: LiveData<Int> get() = _eventDetail
    val goTo: LiveData<NavData> get() = _goTo

    private val _shareEvent by lazy { MutableLiveData<Event>() }
    private val _eventDetail by lazy { MutableLiveData<Int>() }
    private val _onNoUserInfo by lazy { SingleLiveEvent<Unit>() }
    private val _goTo by lazy { MutableLiveData<NavData>() }

    fun onShareClicked(event: Event?) {
        _shareEvent.value = event
    }

    fun onCheckInClicked(eventId: Int) {
        launchDataLoad {
            val userInfo = getUserInfo.execute()
            userInfo?.let { makeCheckIn(eventId, it) } ?: run { onNoUserInfo() }
        }
    }

    private fun onNoUserInfo() {
        _goTo.value = EventCheckInNavData()
    }

    private fun makeCheckIn(eventId: Int, userInfo: UserInfo) {
        launchDataLoad {
            eventCheckIn.execute(eventId, userInfo)
        }
    }
}