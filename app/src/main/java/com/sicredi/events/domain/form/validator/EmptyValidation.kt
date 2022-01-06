package com.sicredi.events.domain.form.validator

class EmptyValidation : BaseValidation {

    override var text: String? = null

    override fun isValid(): Boolean {
        return text?.run { isNotBlank() } ?: false
    }
}