package com.sicredi.events.presentation.view.events.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.domain.interactor.EventCheckIn
import com.sicredi.events.domain.interactor.GetEventsList
import com.sicredi.events.presentation.util.dialog.DialogData
import com.sicredi.events.presentation.util.error.ErrorHandler
import com.sicredi.events.presentation.util.extension.launchDataLoad
import com.sicredi.events.presentation.util.navigation.NavData
import com.sicredi.events.presentation.util.placeholder.Placeholder
import com.sicredi.events.presentation.view.events.details.EventDetailsNavData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ListEventsViewModel constructor(
    private val eventCheckIn: EventCheckIn,
    private val getEventsList: GetEventsList
) : ViewModel(), KoinComponent {

    private val errorHandler: ErrorHandler by inject()

    val placeholder: LiveData<Placeholder> get() = _placeholder
    val goTo: LiveData<NavData> get() = _goTo
    val dialog: LiveData<DialogData> get() = _dialog
    val eventList: LiveData<List<Event>> get() = _eventList
    val showEmptyPlaceholder: LiveData<Boolean> get() = _showEmptyPlaceholder

    private val _placeholder by lazy { MutableLiveData<Placeholder>() }
    private val _goTo by lazy { MutableLiveData<NavData>() }
    private val _dialog by lazy { MutableLiveData<DialogData>() }
    private val _eventList by lazy { MutableLiveData<List<Event>>() }
    private val _showEmptyPlaceholder by lazy { MutableLiveData<Boolean>() }

    init {
        requestEvents()
    }

    fun onEventSelected(event: Event) {
        goTo(EventDetailsNavData(event))
    }

    private fun goTo(navData: NavData) {
        _goTo.postValue(navData)
    }

    fun getAllEvents() {
        requestEvents()
    }

    private fun requestEvents() {
        launchDataLoad(true, onFailure = ::onFailure) {
            val eventsList = getEventsList.execute()
            setEventsList(eventsList)
        }
    }

    private fun setEventsList(eventList: List<Event>?) {
        _eventList.value = eventList
        _showEmptyPlaceholder.value = eventList?.isEmpty()
    }

    private fun onFailure(throwable: Throwable) {
        showErrorDialog(throwable) { requestEvents() }
        _showEmptyPlaceholder.value = true
    }

    private fun showErrorDialog(throwable: Throwable, retryAction: (() -> Unit)) {
        _dialog.value = errorHandler.getDialogData(throwable, retryAction)
    }
}