package com.sicredi.events.presentation.view.events.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sicredi.events.domain.entity.user.UserInfo
import com.sicredi.events.domain.interactor.EventCheckIn
import com.sicredi.events.domain.interactor.GetUserInfo
import com.sicredi.events.presentation.util.dialog.DialogData
import com.sicredi.events.presentation.util.error.ErrorHandler
import com.sicredi.events.presentation.util.extension.launchDataLoad
import com.sicredi.events.presentation.util.navigation.NavData
import com.sicredi.events.presentation.util.placeholder.Placeholder
import com.sicredi.events.presentation.view.events.check_in.EventCheckInNavData

class EventDetailsViewModel(
    private val eventCheckIn: EventCheckIn,
    private val errorHandler: ErrorHandler,
    private val getUserInfo: GetUserInfo
) : ViewModel() {

    val goTo: LiveData<NavData> get() = _goTo
    val placeholder: LiveData<Placeholder> get() = _placeholder
    val dialog: LiveData<DialogData> get() = _dialog

    private val _goTo by lazy { MutableLiveData<NavData>() }
    private val _placeholder by lazy { MutableLiveData<Placeholder>() }
    private val _dialog by lazy { MutableLiveData<DialogData>() }

    fun onCheckInClicked(eventId: Int) {
        launchDataLoad(onPlaceholder = { _placeholder.value = it }) {
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