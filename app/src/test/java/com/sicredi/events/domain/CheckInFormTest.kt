package com.sicredi.events.domain

import com.sicredi.events.domain.form.CheckInForm
import com.sicredi.events.domain.form.validator.EmailValidation
import com.sicredi.events.domain.form.validator.InvalidFieldsException
import org.junit.Before
import org.junit.Test

class CheckInFormTest {

    private lateinit var checkInForm: CheckInForm

    @Test(expected = InvalidFieldsException::class)
    fun `should not be valid if both email and name are incorrect`() {
        checkInForm = CheckInForm("", "marcelo")
        checkInForm.validate()
    }

    @Test(expected = InvalidFieldsException::class)
    fun `should not be valid if only email is correct`() {
        checkInForm = CheckInForm("", "marcelo@hotmail.com")
        checkInForm.validate()
    }

    @Test(expected = InvalidFieldsException::class)
    fun `should not be valid if only name is correct`() {
        checkInForm = CheckInForm("Marcelo", "Marcelo")
        checkInForm.validate()
    }

    @Test
    fun `should be valid if both email and name are correct`() {
        checkInForm = CheckInForm("Marcelo", "marcelo@hotmail.com")
        checkInForm.validate()
    }
}