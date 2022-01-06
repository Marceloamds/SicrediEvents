package com.sicredi.events.domain

import com.sicredi.events.domain.form.validator.EmailValidation
import org.junit.Before
import org.junit.Test

class EmailValidationTest {

    private lateinit var emailValidation: EmailValidation

    @Before
    fun setup() {
        emailValidation = EmailValidation()
    }

    @Test
    fun `should not be valid if email is empty`() {
        emailValidation.text = ""
        assert(!emailValidation.isValid())
    }

    @Test
    fun `should not be valid if email is wrongly formatted`() {
        emailValidation.text = "marcelo@hotmail"
        assert(!emailValidation.isValid())
    }

    @Test
    fun `should be valid if email is formatted correctly`() {
        emailValidation.text = "marcelo@hotmail.com"
        assert(emailValidation.isValid())
    }
}