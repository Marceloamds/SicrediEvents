package com.sicredi.events.presentation.util.error

import android.content.Context
import android.util.Log
import androidx.annotation.StringRes
import com.sicredi.events.BuildConfig
import com.sicredi.events.R
import com.sicredi.events.domain.entity.error.HttpErrorType
import com.sicredi.events.domain.entity.error.RequestException
import com.sicredi.events.presentation.util.dialog.DialogData

class ErrorHandler constructor(
    private val context: Context) {

    fun getDialogData(
        throwable: Throwable,
        retryAction: (() -> Unit)
    ): DialogData? {
        val errorString = getErrorString(throwable)
        return errorString?.let {
            DialogData.error(context, it, res(R.string.global_try_again), retryAction)
        }
    }

    private fun getErrorString(throwable: Throwable): String? {
        if (BuildConfig.DEBUG) Log.e(context.getString(R.string.app_name), throwable.message, throwable)
        return if (throwable is RequestException) {
            when (throwable.httpErrorType) {
                HttpErrorType.UNAUTHORIZED -> res(R.string.error_unauthorized)
                HttpErrorType.TOO_MANY_REQUESTS -> res(R.string.error_too_many_requests)
                else -> null
            }
        } else null
    }

    private fun res(@StringRes stringId: Int) = context.getString(stringId)
}