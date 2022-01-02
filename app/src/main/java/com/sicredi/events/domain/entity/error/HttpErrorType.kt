package com.sicredi.events.domain.entity.error

enum class HttpErrorType {
    UNAUTHORIZED,
    TOO_MANY_REQUESTS;

    companion object {
        fun getErrorForCode(errorCode: Int?): HttpErrorType? {
            return errorCode?.let {
                when (it) {
                    401 -> UNAUTHORIZED
                    429 -> TOO_MANY_REQUESTS
                    else -> null
                }
            }
        }
    }
}