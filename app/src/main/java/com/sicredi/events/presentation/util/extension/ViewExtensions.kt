package com.sicredi.events.presentation.util.extension

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sicredi.events.R
import com.sicredi.events.presentation.util.click.SafeClickListener

fun View.setSafeClickListener(callback: () -> Unit) {
    val intervalInMillis = 1000
    SafeClickListener(callback, intervalInMillis).apply {
        setOnClickListener(this::onClick)
    }
}

// transparent
fun transparentStatusAndNavigation(window: Window) {
    window.decorView.systemUiVisibility =
        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false, window)
    window.statusBarColor = Color.TRANSPARENT
}

fun setWindowFlag(bits: Int, on: Boolean, window: Window) {
    val win: Window = window
    val winParams: WindowManager.LayoutParams = win.attributes
    if (on) {
        winParams.flags = winParams.flags or bits
    } else {
        winParams.flags = winParams.flags and bits.inv()
    }
    win.attributes = winParams
}

@SuppressLint("CheckResult")
fun ImageView.load(url: String?) {
    val requestOptions = RequestOptions().apply {
        placeholder(R.drawable.placeholder)
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