package com.sseclientflow.domain.model

sealed class CallResult<out R, out E> {
    data class Success<R>(val value: R) : CallResult<R, Nothing>()
    data class Error<E>(val error: E) : CallResult<Nothing, E>()
}