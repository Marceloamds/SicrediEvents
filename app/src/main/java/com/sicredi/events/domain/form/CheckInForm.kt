package com.sicredi.events.domain.form

import com.sicredi.events.domain.form.validator.EmailValidation
import com.sicredi.events.domain.form.validator.EmptyValidation
import com.sicredi.events.domain.form.validator.InvalidFieldsException


class CheckInForm(name: String?, email: String?) {

    private val invalidFieldsException = InvalidFieldsException()

    val emailValidation = EmailValidation().apply { text = email }
    val nameValidation = EmptyValidation().apply { text = name }

    fun validate() {
        if (!emailValidation.isValid()) invalidFieldsException.fieldsValidation.add(EMAIL_ID)
        if (!nameValidation.isValid()) invalidFieldsException.fieldsValidation.add(NAME_ID)
        if (invalidFieldsException.fieldsValidation.isNotEmpty()) throw invalidFieldsException
    }

    companion object {
        const val EMAIL_ID = "EMAIL_ID"
        const val NAME_ID = "NAME_ID"
    }
}
