package com.sseclientflow.data.model

internal sealed class EventResponseError {
    data class ParsingError(val info: String?) : EventResponseError()
    data class EventFailure(val info: String) : EventResponseError()
    data class ConnectionFailure(val info: String) : EventResponseError()

    val name: String get() = this::class.java.name
}
