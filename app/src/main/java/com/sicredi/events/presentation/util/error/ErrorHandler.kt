package com.sicredi.events.presentation.util.error

import android.content.Context
import androidx.annotation.StringRes
import com.sicredi.events.R
import com.sicredi.events.domain.entity.error.HttpErrorType
import com.sicredi.events.domain.entity.error.RequestException
import com.sicredi.events.presentation.util.dialog.DialogData

class ErrorHandler constructor(
    private val context: Context
) {

    fun getDialogData(
        throwable: Throwable,
        retryAction: (() -> Unit)
    ): DialogData {
        val errorString = getErrorString(throwable)
       return DialogData.error(context, errorString, res(R.string.global_try_again), retryAction)
    }

    private fun getErrorString(throwable: Throwable): String {
        return if (throwable is RequestException) getRequestExceptionMessage(throwable)
        else res(R.string.error_default)
    }

    private fun getRequestExceptionMessage(throwable: RequestException): String {
        return when (throwable.httpErrorType) {
            HttpErrorType.UNAUTHORIZED -> res(R.string.error_unauthorized)
            HttpErrorType.TOO_MANY_REQUESTS -> res(R.string.error_too_many_requests)
            else -> res(R.string.error_default)
        }
    }

    private fun res(@StringRes stringId: Int) = context.getString(stringId)
}