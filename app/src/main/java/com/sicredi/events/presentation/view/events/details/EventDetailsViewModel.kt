package com.sicredi.events.presentation.view.events.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sicredi.events.domain.entity.user.UserInfo
import com.sicredi.events.domain.interactor.EventCheckIn
import com.sicredi.events.domain.interactor.GetUserInfo
import com.sicredi.events.presentation.util.communication.SingleLiveEvent
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
    val onCheckInSuccess: LiveData<Unit> get() = _onCheckInSuccess

    private val _goTo by lazy { SingleLiveEvent<NavData>() }
    private val _placeholder by lazy { SingleLiveEvent<Placeholder>() }
    private val _dialog by lazy { SingleLiveEvent<DialogData>() }
    private val _onCheckInSuccess by lazy { MutableLiveData<Unit>() }

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
        launchDataLoad(
            onFailure = { onFailure(it, eventId) },
            onPlaceholder = { _placeholder.value = it }
        ) {
            eventCheckIn.execute(eventId, userInfo)
            _onCheckInSuccess.value = Unit
        }
    }

    private fun onFailure(throwable: Throwable, eventId: Int) {
        _dialog.value = errorHandler.getDialogData(throwable) { onCheckInClicked(eventId) }
    }
}