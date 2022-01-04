package com.sicredi.events.domain.form.validator

class EmailValidation(
    override val isRequired: Boolean = true
) : BaseValidation {

    override var text: String? = null

    override fun isValid(): Boolean {
        return text?.run { matches(EMAIL_PATTERN.toRegex()) } ?: false
    }

    companion object {
        const val EMAIL_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
    }
}