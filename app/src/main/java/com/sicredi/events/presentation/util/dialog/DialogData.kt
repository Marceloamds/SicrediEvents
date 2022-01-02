package com.sicredi.events.presentation.util.dialog

import android.content.Context
import com.sicredi.events.R

class DialogData(
    val title: String,
    val message: String,
    val confirmButtonText: String? = null,
    val onConfirm: (() -> Unit)? = null,
    val dismissButtonText: String? = null,
    val onDismiss: (() -> Unit)? = null,
    val cancelable: Boolean? = true
) {
    companion object {

        fun error(
            context: Context,
            message: String,
            confirmButtonText: String? = null,
            onConfirm: (() -> Unit)? = null,
            onDismiss: (() -> Unit)? = null,
            cancelable: Boolean? = true
        ): DialogData {
            return DialogData(
                context.getString(R.string.error_title),
                message,
                confirmButtonText,
                onConfirm,
                null,
                onDismiss,
                cancelable
            )
        }
    }
}
