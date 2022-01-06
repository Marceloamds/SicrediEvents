package com.sicredi.events.presentation.util.extension

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputLayout
import com.sicredi.events.BuildConfig
import com.sicredi.events.R
import com.sicredi.events.presentation.util.click.SafeClickListener

fun View.setSafeClickListener(callback: () -> Unit) {
    val intervalInMillis = 1000
    SafeClickListener(callback, intervalInMillis).apply {
        setOnClickListener(this::onClick)
    }
}

fun View.transparentStatusAndNavigation(window: Window) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, this).isAppearanceLightStatusBars = true
}

@SuppressLint("CheckResult")
fun ImageView.load(url: String?, @DrawableRes placeholder: Int = R.drawable.written_logo) {
    val requestOptions = RequestOptions().apply {
        placeholder(placeholder)
        centerCrop()
    }
    Glide.with(this).load(url).apply(requestOptions).into(this)
}

fun View.setVisible(isVisible: Boolean?) {
    visibility = if (isVisible == true) View.VISIBLE else View.GONE
}

fun TextInputLayout.eraseErrorsWhenTextIsChanged() {
    editText?.onTextChanged { clearError() }
}

fun EditText.onTextChanged(callback: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            text?.let { callback(it.toString()) }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun TextInputLayout.clearError() {
    error = null
    isErrorEnabled = false
}