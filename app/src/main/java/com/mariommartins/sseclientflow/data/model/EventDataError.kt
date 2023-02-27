package com.mariommartins.sseclientflow.data.model

sealed class EventDataError {
    data class ParsingError(val info: String?) : EventDataError()
    data class EventFailure(val info: String) : EventDataError()
    data class ConnectionFailure(val info: String) : EventDataError()

    val name: String get() = this::class.java.name
}
