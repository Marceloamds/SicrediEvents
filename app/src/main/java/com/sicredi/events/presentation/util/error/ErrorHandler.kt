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
            HttpErrorType.BAD_REQUEST -> res(R.string.error_bad_request)
            HttpErrorType.UNAUTHORIZED -> res(R.string.error_unauthorized)
            HttpErrorType.FORBIDDEN -> res(R.string.error_forbidden)
            HttpErrorType.NOT_FOUND -> res(R.string.error_not_found)
            HttpErrorType.TIMEOUT -> res(R.string.error_timeout)
            HttpErrorType.UNPROCESSABLE_ENTITY -> res(R.string.error_unprocessable_entity)
            HttpErrorType.TOO_MANY_REQUESTS -> res(R.string.error_too_many_requests)
            HttpErrorType.INTERNAL_SERVER_ERROR -> res(R.string.error_internal_server_error)
            HttpErrorType.BAD_GATEWAY -> res(R.string.error_bad_gateway)
            HttpErrorType.GATEWAY_TIMEOUT -> res(R.string.error_gateway_timeout)
            null -> res(R.string.error_default)
        }
    }

    private fun res(@StringRes stringId: Int) = context.getString(stringId)
}