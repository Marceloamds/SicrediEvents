package com.sicredi.events.domain.form.validator

interface BaseValidation {

    var text: String?
    fun isValid(): Boolean
}