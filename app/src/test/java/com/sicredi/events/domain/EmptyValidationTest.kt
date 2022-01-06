package com.sicredi.events.domain

import com.sicredi.events.domain.form.validator.EmailValidation
import com.sicredi.events.domain.form.validator.EmptyValidation
import org.junit.Before
import org.junit.Test

class EmptyValidationTest {

    private lateinit var emptyValidation: EmptyValidation

    @Before
    fun setup() {
        emptyValidation = EmptyValidation()
    }

    @Test
    fun `should not be valid if text is empty`() {
        emptyValidation.text = ""
        assert(!emptyValidation.isValid())
    }

    @Test
    fun `should be valid if text is not empty`() {
        emptyValidation.text = "a"
        assert(emptyValidation.isValid())
    }
}