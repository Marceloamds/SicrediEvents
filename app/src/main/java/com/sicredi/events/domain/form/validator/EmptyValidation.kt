package com.sicredi.events.domain.form.validator

class EmptyValidation(
    override val isRequired: Boolean = true
) : BaseValidation {

    override var text: String? = null

    override fun isValid(): Boolean {
        return text?.run { isNotBlank() } ?: false
    }
}