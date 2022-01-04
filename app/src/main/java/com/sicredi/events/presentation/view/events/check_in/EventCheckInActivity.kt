package com.sicredi.events.presentation.view.events.check_in

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sicredi.events.R
import com.sicredi.events.databinding.ActivityEventCheckInBinding
import com.sicredi.events.domain.form.CheckInForm.Companion.EMAIL_ID
import com.sicredi.events.domain.form.CheckInForm.Companion.NAME_ID
import com.sicredi.events.domain.form.validator.InvalidFieldsException
import com.sicredi.events.presentation.util.dialog.DialogData
import com.sicredi.events.presentation.util.extension.eraseErrorsWhenTextIsChanged
import com.sicredi.events.presentation.util.extension.onDialog
import com.sicredi.events.presentation.util.extension.shortToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventCheckInActivity : AppCompatActivity() {

    private val _viewModel: EventCheckInViewModel by viewModel()

    private lateinit var binding: ActivityEventCheckInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_check_in)
        setupUi()
        subscribeUi()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupUi() {
        with(binding) {
            buttonSubmit.setOnClickListener { onSubmitClicked() }
            nameLayout.eraseErrorsWhenTextIsChanged()
            emailLayout.eraseErrorsWhenTextIsChanged()
        }
    }

    private fun subscribeUi() {
        _viewModel.invalidFields.observe(this, ::onInvalidFields)
        _viewModel.onSaveInfoError.observe(this, { onSaveInfoError() })
        _viewModel.onSaveSuccess.observe(this, { onSaveSuccess() })
    }

    private fun onSubmitClicked() {
        with(binding) {
            _viewModel.onSubmitClicked(nameEditText.text.toString(), emailEditText.text.toString())
        }
    }

    private fun onSaveSuccess(){
        shortToast(getString(R.string.check_in_save_info_success))
        finish()
    }

    private fun onInvalidFields(invalidFieldsException: InvalidFieldsException) {
        invalidFieldsException.fieldsValidation.forEach {
            when (it) {
                EMAIL_ID -> binding.emailLayout.error = getString(R.string.check_in_email_error)
                NAME_ID -> binding.nameLayout.error = getString(R.string.check_in_name_error)
            }
        }
    }

    private fun onSaveInfoError() {
        onDialog(
            DialogData(
                getString(R.string.error_title),
                getString(R.string.check_in_save_info_error)
            )
        )
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, EventCheckInActivity::class.java)
    }
}