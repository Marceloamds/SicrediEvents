package com.sicredi.events.domain.form.validator

class InvalidFieldsException : Exception() {

    val fieldsValidation = HashSet<String>()
}