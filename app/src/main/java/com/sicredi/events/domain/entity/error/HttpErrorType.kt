package com.sicredi.events.domain.entity.error

enum class HttpErrorType {
    BAD_REQUEST,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    TIMEOUT,
    UNPROCESSABLE_ENTITY,
    TOO_MANY_REQUESTS,
    INTERNAL_SERVER_ERROR,
    BAD_GATEWAY,
    GATEWAY_TIMEOUT;

    companion object {
        fun getErrorForCode(errorCode: Int?): HttpErrorType? {
            return errorCode?.let {
                when (it) {
                    400 -> BAD_REQUEST
                    401 -> UNAUTHORIZED
                    403 -> FORBIDDEN
                    404 -> NOT_FOUND
                    408 -> TIMEOUT
                    422 -> UNPROCESSABLE_ENTITY
                    429 -> TOO_MANY_REQUESTS
                    500 -> INTERNAL_SERVER_ERROR
                    502 -> BAD_GATEWAY
                    504 -> GATEWAY_TIMEOUT
                    else -> null
                }
            }
        }
    }
}