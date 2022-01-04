package com.sicredi.events.domain.form.validator

interface BaseValidation {

    val isRequired: Boolean
    var text: String?

    fun isValid(): Boolean
}