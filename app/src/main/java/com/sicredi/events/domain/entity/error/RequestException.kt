package com.sicredi.events.domain.entity.error

sealed class RequestException constructor(
    val httpErrorType: HttpErrorType?
) : Exception() {

    class HttpError(errorCode: Int) :
        RequestException(HttpErrorType.getErrorForCode(errorCode))

    class NetworkError : RequestException(null)

    class TimeoutError : RequestException(null)

    class UnexpectedError : RequestException(null)
}