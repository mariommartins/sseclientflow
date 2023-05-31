package com.sseclientflow.domain.model

sealed class EventError(override val message: String? = "") : Exception() {
    object ParsingError : EventError()
    data class EventFailure(override val message: String) : EventError()
    data class ConnectionFailure(override val message: String) : EventError()
}
