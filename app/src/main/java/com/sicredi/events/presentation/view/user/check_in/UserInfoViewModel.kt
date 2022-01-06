package com.sicredi.events.presentation.view.user.check_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sicredi.events.domain.form.CheckInForm
import com.sicredi.events.domain.form.validator.InvalidFieldsException
import com.sicredi.events.domain.interactor.SaveCheckInInfo
import com.sicredi.events.presentation.util.communication.SingleLiveEvent
import com.sicredi.events.presentation.util.extension.launchDataLoad
import com.sicredi.events.presentation.util.extension.tryCatch

class UserInfoViewModel constructor(
    private val saveCheckInInfo: SaveCheckInInfo
) : ViewModel() {

    val onSaveInfoError: LiveData<Unit> get() = _onSaveInfoError
    val onSaveSuccess: LiveData<Unit> get() = _onSaveSuccess
    val invalidFields: LiveData<InvalidFieldsException> get() = _invalidFields

    private val _onSaveInfoError by lazy { SingleLiveEvent<Unit>() }
    private val _onSaveSuccess by lazy { SingleLiveEvent<Unit>() }
    private val _invalidFields by lazy { SingleLiveEvent<InvalidFieldsException>() }

    fun onSubmitClicked(name: String?, email: String?) {
        tryCatch(onFailure = ::onFailure) {
            saveCheckInInfo.execute(CheckInForm(name, email))
            _onSaveSuccess.value = Unit
        }
    }

    private fun onFailure(throwable: Throwable) {
        if (throwable is InvalidFieldsException) _invalidFields.value = throwable
        else _onSaveInfoError.value = Unit
    }
}