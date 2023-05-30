package com.mariommartins.sseclientflow.data.model

sealed class EventResponseError {
    data class ParsingError(val info: String?) : EventResponseError()
    data class EventFailure(val info: String) : EventResponseError()
    data class ConnectionFailure(val info: String) : EventResponseError()

    val name: String get() = this::class.java.name
}
